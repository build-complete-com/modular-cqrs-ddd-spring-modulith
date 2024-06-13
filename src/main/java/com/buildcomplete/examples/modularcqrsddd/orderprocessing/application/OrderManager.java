package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;

import java.util.Map;

public interface OrderManager {

    OrderId submitOrder(Map<ProductId, Integer> productQuantitiesMap);
}
