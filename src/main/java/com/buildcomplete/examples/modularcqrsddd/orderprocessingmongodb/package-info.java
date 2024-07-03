@ApplicationModule(
    displayName = "Order Processing MongoDB Adapter",
    allowedDependencies = {
        "orderprocessing::repository"
    }
)
@SecondaryAdapter
package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import org.jmolecules.architecture.hexagonal.SecondaryAdapter;
import org.springframework.modulith.ApplicationModule;