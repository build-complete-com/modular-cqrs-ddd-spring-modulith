package com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain;

import com.buildcomplete.examples.modularcqrsddd.domainframework.DomainRepository;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.OrderId;

public interface OrderRepository extends DomainRepository<Order, OrderId> {

}
