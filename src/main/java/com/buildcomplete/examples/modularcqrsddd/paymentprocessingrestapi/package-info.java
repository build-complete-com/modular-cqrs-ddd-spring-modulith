@ApplicationModule(
    displayName = "Payment Processing REST API Adapter",
    allowedDependencies = {
        "paymentprocessing::application",
        "domainsharedkernel"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingrestapi;

import org.springframework.modulith.ApplicationModule;