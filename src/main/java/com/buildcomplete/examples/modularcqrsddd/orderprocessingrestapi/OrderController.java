package com.buildcomplete.examples.modularcqrsddd.orderprocessingrestapi;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.application.OrderManager;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import java.util.LinkedHashMap;
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
    OrderId submitOrder(LinkedHashMap<ProductId, Integer> productQuantities) {
        return orderManager.submitOrder(productQuantities);
    }
}
