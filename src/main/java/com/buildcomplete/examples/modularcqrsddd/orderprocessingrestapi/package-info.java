@ApplicationModule(
    displayName = "Order Processing REST API Adapter",
    allowedDependencies = {
        "orderprocessingapplication",
        "orderprocessingdomain",
        "domainsharedkernel",
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingrestapi;

import org.springframework.modulith.ApplicationModule;