package com.buildcomplete.examples.modularcqrsddd.write.adapters.rest.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.write.application.orderprocessing.OrderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
class OrderController {
    private final OrderManager orderManager;

    @PostMapping
    OrderId submitOrder(Map<ProductId, Integer> productQuantities) {
        return orderManager.submitOrder(productQuantities);
    }
}
