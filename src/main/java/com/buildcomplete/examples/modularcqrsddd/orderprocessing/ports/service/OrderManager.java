package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service;

import java.util.UUID;

public interface OrderManager {

  UUID submitOrder(OrderSubmissionDto orderSubmission);
}
