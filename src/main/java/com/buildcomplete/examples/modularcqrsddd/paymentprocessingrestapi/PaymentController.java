package com.buildcomplete.examples.modularcqrsddd.paymentprocessingrestapi;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.service.PaymentManagerPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
class PaymentController {
  private final PaymentManagerPort paymentManagerPort;

  @PostMapping
  UUID startPayment(@RequestBody UUID orderId) {
    return paymentManagerPort.startPayment(orderId);
  }

  @PostMapping("/complete-broker-payment")
  void completeBrokerPayment(@RequestBody String brokerPaymentId) {
    paymentManagerPort.completePayment(brokerPaymentId);
  }
}
