package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.LineItem;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.LineItemId;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.ProductId;
import org.springframework.stereotype.Component;

@Component
class LineItemDocumentConverter {

    LineItemDocument convert(LineItem lineItem) {
        LineItemDocument document = new LineItemDocument();
        document.setId(lineItem.getId().getValue());
        document.setProductId(lineItem.getProductId().getValue());
        document.setQuantity(lineItem.getQuantity());
        document.setPricePerUnit(lineItem.getPricePerUnit());
        return document;
    }

    LineItem convert(LineItemDocument entity) {
        return LineItem.reconstitutingBuilder()
                .id(LineItemId.of(entity.getId()))
                .productId(ProductId.of(entity.getProductId()))
                .quantity(entity.getQuantity())
                .pricePerUnit(entity.getPricePerUnit())
                .build();
    }
}
