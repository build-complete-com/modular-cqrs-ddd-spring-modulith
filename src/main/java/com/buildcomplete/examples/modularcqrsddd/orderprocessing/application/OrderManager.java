package com.buildcomplete.examples.modularcqrsddd.orderprocessing.application;

import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.domain.ProductId;
import java.util.LinkedHashMap;

public interface OrderManager {

    OrderId submitOrder(LinkedHashMap<ProductId, Integer> productQuantitiesMap);
}
