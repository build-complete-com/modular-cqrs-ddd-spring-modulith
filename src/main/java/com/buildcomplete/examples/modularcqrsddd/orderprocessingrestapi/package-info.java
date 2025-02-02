@ApplicationModule(
    displayName = "Order Processing REST API Adapter",
    allowedDependencies = {
        "orderprocessing::service"
    }
)
@PrimaryAdapter
package com.buildcomplete.examples.modularcqrsddd.orderprocessingrestapi;

import org.jmolecules.architecture.hexagonal.PrimaryAdapter;
import org.springframework.modulith.ApplicationModule;