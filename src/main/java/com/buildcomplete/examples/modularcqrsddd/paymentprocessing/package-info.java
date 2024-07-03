@ApplicationModule(
    displayName = "Payment Processing",
    allowedDependencies = {
        "paymentprocessing::repository", // this is workaround for bug https://github.com/spring-projects/spring-modulith/issues/660
        "hexagoncore::domainsharedkernel",
        "hexagoncore::domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessing;

import org.springframework.modulith.ApplicationModule;