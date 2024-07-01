package com.buildcomplete.examples.modularcqrsddd.orderprocessingrestapi;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderManager;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.service.OrderSubmissionDto;
import java.util.LinkedHashMap;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
class OrderController {
    private final OrderManager orderManager;

    @PostMapping
    UUID submitOrder(OrderSubmissionDto orderSubmission) {
        return orderManager.submitOrder(orderSubmission);
    }
}
