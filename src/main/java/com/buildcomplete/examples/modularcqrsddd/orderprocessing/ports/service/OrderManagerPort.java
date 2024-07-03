package com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service;

import java.util.UUID;

public interface OrderManagerPort {

  UUID submitOrder(OrderSubmissionDto orderSubmission);
}
