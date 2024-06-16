package com.buildcomplete.examples.modularcqrsddd.orderprocessing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.buildcomplete.examples.modularcqrsddd.AbstractIntegrationTest;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderPayedEvent;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.OrderManager;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import java.util.LinkedHashMap;
import java.util.Optional;
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

@ApplicationModuleTest(extraIncludes = {
    "mongoconfig"
})
@ActiveProfiles("tests")
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
class OrderProcessingIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private OrderManager orderManager;
  @MockBean
  private OrderRepository orderRepository;
  @Captor
  private ArgumentCaptor<Order> orderArgumentCaptor;

  @Test
  @DisplayName("""
      GIVEN product quantities 
       WHEN submitting order 
       THEN publishes order submitted event 
       AND persists order""")
  void handlesOrderSubmission(Scenario scenario) {
    ProductId firstProductId = ProductId.randomProductId();
    ProductId secondProductId = ProductId.randomProductId();
    var productQuantities = new LinkedHashMap<ProductId, Integer>();
    productQuantities.put(firstProductId, 2);
    productQuantities.put(secondProductId, 3);

    scenario.stimulate(() -> orderManager.submitOrder(productQuantities))
        .andWaitForEventOfType(OrderSubmittedEvent.class)
        .toArriveAndVerify((publishedEvent, returnedOrderId) -> {
          assertThat(publishedEvent.getOrderId()).isEqualTo(returnedOrderId);
          assertThat(publishedEvent.getLineItems()).isNotNull().hasSize(2);
          assertThat(publishedEvent.getLineItems().get(0).getProductId()).isEqualTo(firstProductId);
          assertThat(publishedEvent.getLineItems().get(0).getQuantity()).isEqualTo(2);
          assertThat(publishedEvent.getLineItems().get(1).getProductId()).isEqualTo(secondProductId);
          assertThat(publishedEvent.getLineItems().get(1).getQuantity()).isEqualTo(3);

          verify(orderRepository).save(orderArgumentCaptor.capture());
          Order capturedOrder = orderArgumentCaptor.getValue();
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
    OrderId orderId = OrderId.randomOrderId();
    Order order = Order.reconstitutingBuilder().id(orderId).build();
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
    PaymentId paymentId = PaymentId.randomPaymentId();
    PaymentCompletedEvent paymentCompletedEvent = new PaymentCompletedEvent(paymentId, orderId);

    scenario.publish(() -> paymentCompletedEvent)
        .andWaitForEventOfType(OrderPayedEvent.class)
        .toArriveAndVerify(publishedEvent -> {
          assertThat(publishedEvent.getOrderId()).isEqualTo(orderId);

          verify(orderRepository).save(orderArgumentCaptor.capture());
          Order capturedOrder = orderArgumentCaptor.getValue();
          assertThat(capturedOrder.getId()).isEqualTo(orderId);
          assertThat(capturedOrder.isPaymentComplete()).isTrue();
        });
  }
}
