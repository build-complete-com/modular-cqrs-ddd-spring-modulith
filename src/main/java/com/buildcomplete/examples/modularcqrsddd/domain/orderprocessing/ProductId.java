package com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class ProductId {
    private final UUID value;

    public static ProductId randomProductId() {
        return ProductId.of(UUID.randomUUID());
    }
}
