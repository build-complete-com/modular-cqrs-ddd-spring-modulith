package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class ProductId implements Comparable<ProductId> {
    private final UUID value;

    public static ProductId randomProductId() {
        return ProductId.of(UUID.randomUUID());
    }

    @Override
    public int compareTo(ProductId o) {
        return this.value.compareTo(o.value);
    }
}
