@ApplicationModule(
    displayName = "Order Processing Application Layer",
    allowedDependencies = {
        "orderprocessingdomain",
        "integrationevents",
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingapplication;

import org.springframework.modulith.ApplicationModule;