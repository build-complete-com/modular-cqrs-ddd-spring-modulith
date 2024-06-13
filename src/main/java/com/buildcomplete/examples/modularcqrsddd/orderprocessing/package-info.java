@ApplicationModule(
    displayName = "Order Processing",
    allowedDependencies = {
        "integrationevents",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessing;

import org.springframework.modulith.ApplicationModule;