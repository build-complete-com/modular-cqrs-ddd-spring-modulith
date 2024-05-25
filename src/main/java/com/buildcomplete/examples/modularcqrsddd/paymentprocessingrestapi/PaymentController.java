package com.buildcomplete.examples.modularcqrsddd.paymentprocessingrestapi;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication.PaymentManager;
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
