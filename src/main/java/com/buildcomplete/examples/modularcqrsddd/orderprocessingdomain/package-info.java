@ApplicationModule(
    displayName = "Order Processing Domain Layer",
    allowedDependencies = {
        "domainsharedkernel",
        "domainframework"
    }
)
package com.buildcomplete.examples.modularcqrsddd.orderprocessingdomain;

import org.springframework.modulith.ApplicationModule;