@ApplicationModule(
    displayName = "Payment Processing MongoDB Adapter",
    allowedDependencies = {
        "paymentprocessing::domain",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import org.springframework.modulith.ApplicationModule;