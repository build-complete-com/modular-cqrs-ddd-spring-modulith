package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.Order;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.OrderSubmittedEvent;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest
@ConfigurationProperties("spring.modulith.events.mongodb.transaction-management.enabled=false")
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
class OrderProcessingIntegrationTest {
  @Autowired
  private OrderManager orderManager;
  @MockBean
  private OrderRepository orderRepository;
  @Captor
  private ArgumentCaptor<Order> orderArgumentCaptor;

  @Test
  @DisplayName("GIVEN WHEN THEN")
  void test(Scenario scenario) {
    ProductId firstProductId = ProductId.randomProductId();
    ProductId secondProductId = ProductId.randomProductId();
    var productQuantities = Map.of(firstProductId, 2, secondProductId, 3);

    scenario.stimulate(() -> orderManager.submitOrder(productQuantities))
        .andWaitForEventOfType(OrderSubmittedEvent.class)
        .toArriveAndVerify((publishedEvent, returnedOrderId) -> {
          assertThat(publishedEvent.getOrderId().getValue()).isEqualTo(returnedOrderId);
          assertThat(publishedEvent.getLineItems()).isNotNull().hasSize(2);
          assertThat(publishedEvent.getLineItems().get(0).getProductId()).isEqualTo(firstProductId);
          assertThat(publishedEvent.getLineItems().get(0).getQuantity()).isEqualTo(2);
          assertThat(publishedEvent.getLineItems().get(1).getProductId()).isEqualTo(secondProductId);
          assertThat(publishedEvent.getLineItems().get(1).getQuantity()).isEqualTo(3);

          verify(orderRepository).save(orderArgumentCaptor.capture());
          Order capturedOrder = orderArgumentCaptor.getValue();
          assertThat(capturedOrder.getId()).isEqualTo(returnedOrderId);
          assertThat(publishedEvent.getLineItems()).isNotNull().hasSize(2);
          assertThat(publishedEvent.getLineItems().get(0).getProductId()).isEqualTo(firstProductId);
          assertThat(publishedEvent.getLineItems().get(0).getQuantity()).isEqualTo(2);
          assertThat(publishedEvent.getLineItems().get(1).getProductId()).isEqualTo(secondProductId);
          assertThat(publishedEvent.getLineItems().get(1).getQuantity()).isEqualTo(3);
        });
  }
}
