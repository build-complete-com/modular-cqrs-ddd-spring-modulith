package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;

import java.util.Map;
import java.util.SortedMap;

public interface OrderManager {

    OrderId submitOrder(SortedMap<ProductId, Integer> productQuantitiesMap);
}
