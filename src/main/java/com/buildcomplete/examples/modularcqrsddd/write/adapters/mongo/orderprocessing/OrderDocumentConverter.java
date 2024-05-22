package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.LineItem;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.Order;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class OrderDocumentConverter {
    private final LineItemDocumentConverter lineItemDocumentConverter;

    OrderDocument convert(Order order) {
        OrderDocument document = new OrderDocument();
        document.setId(order.getId().getValue());
        document.setPaymentComplete(order.isPaymentComplete());
        List<LineItemDocument> lineItems = order.getLineItems().stream()
                .map(lineItemDocumentConverter::convert)
                .toList();
        document.setLineItems(lineItems);
        return document;
    }

    Order convert(OrderDocument document) {
        List<LineItem> lineItems = document.getLineItems().stream()
                .map(lineItemDocumentConverter::convert)
                .toList();
        return Order.reconstitutingBuilder()
                .id(OrderId.of(document.getId()))
                .version(document.getVersion())
                .paymentComplete(document.isPaymentComplete())
                .lineItems(lineItems)
                .build();
    }
}
