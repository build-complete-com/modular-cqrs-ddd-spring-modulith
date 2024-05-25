@ApplicationModule(
    displayName = "Payment Processing Domain Layer",
    allowedDependencies = {
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain;

import org.springframework.modulith.ApplicationModule;