package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain;

import com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework.DomainEntity;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class LineItem extends DomainEntity<LineItemId> {
    ProductId productId;
    int quantity;
    BigDecimal pricePerUnit;

    @Builder(builderMethodName = "reconstitutingBuilder", builderClassName = "ReconstitutingBuilder", toBuilder = true)
    public LineItem(LineItemId id, ProductId productId, int quantity, BigDecimal pricePerUnit) {
        super(id);
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    @Builder(builderMethodName = "constitutingBuilder", builderClassName = "ConstitutingBuilder")
    public LineItem(ProductId productId, int quantity, BigDecimal pricePerUnit) {
        super(LineItemId.randomLineItemId());
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }
}
