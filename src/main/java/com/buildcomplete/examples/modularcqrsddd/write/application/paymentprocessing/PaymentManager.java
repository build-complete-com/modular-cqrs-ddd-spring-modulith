package com.buildcomplete.examples.modularcqrsddd.write.application.paymentprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentId;

public interface PaymentManager {

  PaymentId startPayment(OrderId orderId);

  void completePayment(String brokerPaymentId);
}
