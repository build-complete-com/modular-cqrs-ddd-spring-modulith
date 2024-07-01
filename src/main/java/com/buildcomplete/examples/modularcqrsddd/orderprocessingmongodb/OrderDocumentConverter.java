package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.LineItemDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderDocumentConverter {
    private final LineItemDocumentConverter lineItemDocumentConverter;

    OrderDocument convert(OrderDto order) {
        OrderDocument document = new OrderDocument();
        document.setId(order.getId());
        document.setPaymentComplete(order.isPaymentComplete());
        List<LineItemDocument> lineItems = order.getLineItems().stream()
                .map(lineItemDocumentConverter::convert)
                .toList();
        document.setLineItems(lineItems);
        return document;
    }

    OrderDto convert(OrderDocument document) {
        List<LineItemDto> lineItemDtos = document.getLineItems().stream()
                .map(lineItemDocumentConverter::convert)
                .toList();
        return OrderDto.builder()
                .id(document.getId())
                .version(document.getVersion())
                .paymentComplete(document.isPaymentComplete())
                .lineItems(lineItemDtos)
                .build();
    }
}
