package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.orderprocessing;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
class OrderDocument {
    private UUID id;
    private boolean paymentComplete;
    private List<LineItemDocument> lineItems;
    private Long version;
}
