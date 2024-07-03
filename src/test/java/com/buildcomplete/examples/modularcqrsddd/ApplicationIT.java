package com.buildcomplete.examples.modularcqrsddd;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application.domain.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainsharedkernel.OrderId;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.Map;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
class ApplicationIT extends AbstractIntegrationTest {

  private static final String PRODUCT_ID = "05ddc90c-0cf6-4bbf-9b66-a57177984cea";
  private static final Duration POLL_DELAY = Duration.ofSeconds(1);
  private static final Duration POLL_TIMEOUT = Duration.ofSeconds(30);

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testHappyPath() throws Exception {
    String jsonProductQuantities = objectMapper.writeValueAsString(Map.of(PRODUCT_ID, 1));

    String orderIdString = mockMvc.perform(post("/order")
            .contentType(APPLICATION_JSON)
            .content(jsonProductQuantities))
        .andExpect(status().isOk())
        .andExpect(jsonPath("value", notNullValue()))
        .andReturn()
        .getResponse()
        .getContentAsString();

    OrderId orderId = objectMapper.readValue(orderIdString, OrderId.class);

    String paymentIdString = mockMvc.perform(post("/payment")
            .contentType(APPLICATION_JSON)
            .content(orderIdString))
        .andExpect(status().isOk())
        .andExpect(jsonPath("value", notNullValue()))
        .andReturn()
        .getResponse()
        .getContentAsString();

    PaymentId paymentId = objectMapper.readValue(paymentIdString, PaymentId.class);

    Awaitility.await()
        .pollDelay(POLL_DELAY)
        .atMost(POLL_TIMEOUT)
        .untilAsserted(() -> {
          mockMvc.perform(get("/payments").contentType(APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.[0].paymentId", is(paymentId.getValue().toString())))
              .andExpect(jsonPath("$.[0].paymentState", is("BROKER_PAYMENT_ID_ASSIGNED")));
        });

    String brokerPaymentId =
        "1"; // TODO Do not hard code this value. Retrieve this value from payment aggregate or from read model or mock response from payment broker API
    mockMvc.perform(post("/payment/complete-broker-payment")
            .contentType(APPLICATION_JSON)
            .content(brokerPaymentId))
        .andExpect(status().isOk());

    Awaitility.await()
        .pollDelay(POLL_DELAY)
        .atMost(POLL_TIMEOUT)
        .untilAsserted(() -> {
          mockMvc.perform(get("/orders").contentType(APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.[0].id", is(orderId.getValue().toString())))
              .andExpect(jsonPath("$.[0].paymentComplete", is(true)));
        });
  }
}
