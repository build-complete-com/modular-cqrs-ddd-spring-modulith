package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.LineItemDto;
import org.springframework.stereotype.Component;

@Component
class LineItemDocumentConverter {

    LineItemDocument convert(LineItemDto lineItem) {
        LineItemDocument document = new LineItemDocument();
        document.setId(lineItem.getId());
        document.setProductId(lineItem.getProductId());
        document.setQuantity(lineItem.getQuantity());
        document.setPricePerUnit(lineItem.getPricePerUnit());
        return document;
    }

    LineItemDto convert(LineItemDocument entity) {
        return LineItemDto.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .pricePerUnit(entity.getPricePerUnit())
                .build();
    }
}
