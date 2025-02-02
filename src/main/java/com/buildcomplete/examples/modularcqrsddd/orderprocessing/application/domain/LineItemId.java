package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class LineItemId {
    private final UUID value;

    public static LineItemId randomLineItemId() {
        return LineItemId.of(UUID.randomUUID());
    }
}
