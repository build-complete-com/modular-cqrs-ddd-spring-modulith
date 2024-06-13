package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;

public interface PaymentManager {

  PaymentId startPayment(OrderId orderId);

  void completePayment(String brokerPaymentId);
}
