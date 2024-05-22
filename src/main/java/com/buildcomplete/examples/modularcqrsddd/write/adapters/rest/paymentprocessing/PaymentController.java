package com.buildcomplete.examples.modularcqrsddd.write.adapters.rest.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.write.application.paymentprocessing.PaymentManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
class PaymentController {
  private final PaymentManager paymentManager;

  @PostMapping
  PaymentId startPayment(@RequestBody OrderId orderId) {
    return paymentManager.startPayment(orderId);
  }

  @PostMapping("/complete-broker-payment")
  void completeBrokerPayment(@RequestBody String brokerPaymentId) {
    paymentManager.completePayment(brokerPaymentId);
  }
}
