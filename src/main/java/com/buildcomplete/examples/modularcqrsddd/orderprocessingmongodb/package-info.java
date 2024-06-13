@ApplicationModule(
    displayName = "Order Processing MongoDB Adapter",
    allowedDependencies = {
        "orderprocessing::domain",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import org.springframework.modulith.ApplicationModule;