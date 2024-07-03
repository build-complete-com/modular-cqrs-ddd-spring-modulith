@ApplicationModule(
    displayName = "Payment Processing REST API Adapter",
    allowedDependencies = {
        "paymentprocessing::service"
    }
)
@PrimaryAdapter
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingrestapi;

import org.jmolecules.architecture.hexagonal.PrimaryAdapter;
import org.springframework.modulith.ApplicationModule;