@ApplicationModule(
    displayName = "Read Model",
    allowedDependencies = {
        "domainsharedkernel",
        "orderprocessing::events",
        "paymentprocessing::events"
    }
)
package com.buildcomplete.examples.modularcqrsddd.readmodel;

import org.springframework.modulith.ApplicationModule;