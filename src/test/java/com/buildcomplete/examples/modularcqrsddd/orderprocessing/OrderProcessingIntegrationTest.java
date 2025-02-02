package com.buildcomplete.examples.modularcqrsddd.orderprocessing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.buildcomplete.examples.modularcqrsddd.AbstractIntegrationTest;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderPayedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.events.OrderSubmittedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDtoRepositoryPort;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderManagerPort;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderSubmissionDto;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsTemplate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;

@ApplicationModuleTest(extraIncludes = {"hexagoncore"})
@ActiveProfiles("tests")
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
class OrderProcessingIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private OrderManagerPort orderManagerPort;
  @MockBean
  private OrderDtoRepositoryPort orderRepository;
  @MockBean
  private SnsTemplate snsTemplate;

  @Captor
  private ArgumentCaptor<OrderDto> orderArgumentCaptor;
  @Captor
  private ArgumentCaptor<SnsNotification> snsNotificationCaptor;

  @Test
  @DisplayName("""
      GIVEN product quantities
       WHEN submitting order
       THEN publishes order submitted event
       AND persists order""")
  void handlesOrderSubmission(Scenario scenario) {
    UUID firstProductId = UUID.randomUUID();
    UUID secondProductId = UUID.randomUUID();
    var orderSubmission = new OrderSubmissionDto(List.of(
        new OrderSubmissionDto.Entry(firstProductId, 2),
        new OrderSubmissionDto.Entry(secondProductId, 3)
    ));

    scenario.stimulate(() -> orderManagerPort.submitOrder(orderSubmission))
        .andWaitForEventOfType(OrderSubmittedPortEvent.class)
        .toArriveAndVerify((publishedEvent, returnedOrderId) -> {
          assertThat(publishedEvent.getOrderId()).isEqualTo(returnedOrderId);
          assertThat(publishedEvent.getLineItems()).isNotNull().hasSize(2);
          assertThat(publishedEvent.getLineItems().get(0).getProductId()).isEqualTo(firstProductId);
          assertThat(publishedEvent.getLineItems().get(0).getQuantity()).isEqualTo(2);
          assertThat(publishedEvent.getLineItems().get(1).getProductId()).isEqualTo(secondProductId);
          assertThat(publishedEvent.getLineItems().get(1).getQuantity()).isEqualTo(3);

          verify(orderRepository).save(orderArgumentCaptor.capture());
          OrderDto capturedOrder = orderArgumentCaptor.getValue();
          assertThat(capturedOrder.getId()).isEqualTo(returnedOrderId);
          assertThat(capturedOrder.getLineItems()).isNotNull().hasSize(2);
          assertThat(capturedOrder.getLineItems().get(0).getProductId()).isEqualTo(firstProductId);
          assertThat(capturedOrder.getLineItems().get(0).getQuantity()).isEqualTo(2);
          assertThat(capturedOrder.getLineItems().get(1).getProductId()).isEqualTo(secondProductId);
          assertThat(capturedOrder.getLineItems().get(1).getQuantity()).isEqualTo(3);
        });
  }

  @Test
  @DisplayName("""
      GIVEN payment complete event
       WHEN handling then event
       THEN publishes order payed event
       AND persists order""")
  void handlesCompletedPayment(Scenario scenario) {
    UUID orderId = UUID.randomUUID();
    OrderDto order = OrderDto.builder().id(orderId).lineItems(List.of()).build();
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
    UUID paymentId = UUID.randomUUID();
    PaymentCompletedPortEvent paymentCompletedEvent = new PaymentCompletedPortEvent(paymentId, orderId);

    scenario.publish(() -> paymentCompletedEvent)
        .andWaitForEventOfType(OrderPayedPortEvent.class)
        .toArriveAndVerify(publishedEvent -> {
          assertThat(publishedEvent.getOrderId()).isEqualTo(orderId);

          verify(snsTemplate).sendNotification(eq("modular-monolith-topic.fifo"), snsNotificationCaptor.capture());
          SnsNotification snsNotification = snsNotificationCaptor.getValue();
          assertThat(snsNotification.getPayload()).isEqualTo(publishedEvent);

          verify(orderRepository).save(orderArgumentCaptor.capture());
          OrderDto capturedOrder = orderArgumentCaptor.getValue();
          assertThat(capturedOrder.getId()).isEqualTo(orderId);
          assertThat(capturedOrder.isPaymentComplete()).isTrue();
        });
  }
}
