@ApplicationModule(
    displayName = "Payment Processing MongoDB Adapter",
    allowedDependencies = {
        "paymentprocessing::repository"
    }
)
@SecondaryAdapter
package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import org.jmolecules.architecture.hexagonal.SecondaryAdapter;
import org.springframework.modulith.ApplicationModule;