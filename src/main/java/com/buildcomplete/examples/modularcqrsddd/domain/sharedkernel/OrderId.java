package com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

import java.util.UUID;

@Value
public class OrderId {
    private final UUID value;

    public static OrderId randomOrderId() {
        return OrderId.of(UUID.randomUUID());
    }

    @JsonCreator
    public static OrderId of(UUID value) {
        return new OrderId(value);
    }
}
