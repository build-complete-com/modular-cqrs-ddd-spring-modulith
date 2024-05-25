@ApplicationModule(
    displayName = "Payment Processing MongoDB Adapter",
    allowedDependencies = {
        "paymentprocessingapplication",
        "paymentprocessingdomain",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import org.springframework.modulith.ApplicationModule;