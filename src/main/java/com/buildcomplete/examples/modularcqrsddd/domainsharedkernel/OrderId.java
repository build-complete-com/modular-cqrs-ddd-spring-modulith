package com.buildcomplete.examples.modularcqrsddd.domainsharedkernel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class OrderId {
    private final UUID value;

    public static OrderId randomOrderId() {
        return OrderId.of(UUID.randomUUID());
    }

    @JsonCreator
    public static OrderId of(@JsonProperty("value") UUID value) {
        return new OrderId(value);
    }
}
