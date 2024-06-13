@ApplicationModule(
    displayName = "Payment Processing",
    allowedDependencies = {
        "domainsharedkernel",
        "domainframework",
        "integrationevents"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessing;

import org.springframework.modulith.ApplicationModule;