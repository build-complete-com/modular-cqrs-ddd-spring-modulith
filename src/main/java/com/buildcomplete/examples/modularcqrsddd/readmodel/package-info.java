@ApplicationModule(
    displayName = "Read Model",
    allowedDependencies = {
        "domainsharedkernel",
        "orderprocessingdomain",
        "paymentprocessingdomain"
    }
)
package com.buildcomplete.examples.modularcqrsddd.readmodel;

import org.springframework.modulith.ApplicationModule;