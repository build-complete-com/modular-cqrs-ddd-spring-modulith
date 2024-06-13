@ApplicationModule(
    displayName = "Order Processing REST API Adapter",
    allowedDependencies = {
        "orderprocessing::application",
        "orderprocessing::domain",
        "domainsharedkernel",
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingrestapi;

import org.springframework.modulith.ApplicationModule;