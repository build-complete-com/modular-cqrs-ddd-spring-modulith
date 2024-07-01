package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.service;

import java.util.UUID;

public interface PaymentManager {

  UUID startPayment(UUID orderId);

  void completePayment(String brokerPaymentId);
}
