@ApplicationModule(
    displayName = "Payment Processing REST API Adapter",
    allowedDependencies = {
        "paymentprocessingapplication",
        "domainsharedkernel"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingrestapi;

import org.springframework.modulith.ApplicationModule;