package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.OrderSubmission;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.domain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderSubmissionDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class OrderSubmissionConverter {

  OrderSubmission convert(OrderSubmissionDto orderSubmissionDto) {
    List<OrderSubmission.Entry> entries = orderSubmissionDto.getEntries().stream().map(this::convert).toList();
    return new OrderSubmission(entries);
  }

  private OrderSubmission.Entry convert(OrderSubmissionDto.Entry entryDto) {
    return new OrderSubmission.Entry(ProductId.of(entryDto.getProductId()), entryDto.getQuantity());
  }
}
