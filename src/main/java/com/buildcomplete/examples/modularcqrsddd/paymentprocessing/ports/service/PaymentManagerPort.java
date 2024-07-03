package com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.service;

import java.util.UUID;

public interface PaymentManagerPort {

  UUID startPayment(UUID orderId);

  void completePayment(String brokerPaymentId);
}
