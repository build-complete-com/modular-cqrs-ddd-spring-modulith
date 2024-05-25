@ApplicationModule(
    displayName = "Order Processing MongoDB Adapter",
    allowedDependencies = {
        "orderprocessingapplication",
        "orderprocessingdomain",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import org.springframework.modulith.ApplicationModule;