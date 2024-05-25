package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;

import java.util.Map;

public interface OrderManager {

    OrderId submitOrder(Map<ProductId, Integer> productQuantitiesMap);
}
