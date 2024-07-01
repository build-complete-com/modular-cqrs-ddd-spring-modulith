@ApplicationModule(
    displayName = "Order Processing",
    allowedDependencies = {
        "orderprocessing::repository", // this is workaround for bug https://github.com/spring-projects/spring-modulith/issues/660
        "paymentprocessing::events",
        "integrationevents",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessing;

import org.springframework.modulith.ApplicationModule;