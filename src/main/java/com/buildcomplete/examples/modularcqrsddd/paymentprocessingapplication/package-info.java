@ApplicationModule(
    displayName = "Payment Processing Application Layer",
    allowedDependencies = {
        "paymentprocessingdomain",
        "domainsharedkernel",
        "domainframework",
        "integrationevents"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingapplication;

import org.springframework.modulith.ApplicationModule;