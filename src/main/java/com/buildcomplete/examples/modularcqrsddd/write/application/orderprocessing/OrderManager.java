package com.buildcomplete.examples.modularcqrsddd.write.application.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;

import java.util.Map;

public interface OrderManager {

    OrderId submitOrder(Map<ProductId, Integer> productQuantitiesMap);
}
