package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;

public interface PaymentManager {

  PaymentId startPayment(OrderId orderId);

  void completePayment(String brokerPaymentId);
}
