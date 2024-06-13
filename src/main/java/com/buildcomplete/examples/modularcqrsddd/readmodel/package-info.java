@ApplicationModule(
    displayName = "Read Model",
    allowedDependencies = {
        "domainsharedkernel",
        "orderprocessing::domain",
        "paymentprocessing::domain"
    }
)
package com.buildcomplete.examples.modularcqrsddd.readmodel;

import org.springframework.modulith.ApplicationModule;