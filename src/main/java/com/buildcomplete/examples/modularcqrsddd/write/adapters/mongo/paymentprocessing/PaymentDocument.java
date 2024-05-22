package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.paymentprocessing;

import lombok.Data;

import java.util.UUID;

@Data
class PaymentDocument {
    private UUID id;
    private UUID orderId;
    private String brokerPaymentId;
    private boolean complete;
    private Long version;
}
