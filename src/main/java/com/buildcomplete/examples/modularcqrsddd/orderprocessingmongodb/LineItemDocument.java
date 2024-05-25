package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
class LineItemDocument {
    private UUID id;
    private UUID productId;
    private int quantity;
    private BigDecimal pricePerUnit;
}
