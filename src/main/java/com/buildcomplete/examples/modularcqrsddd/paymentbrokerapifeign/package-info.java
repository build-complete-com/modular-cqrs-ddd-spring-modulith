@ApplicationModule(
    displayName = "Payment Broker API Feign Client Adapter",
    allowedDependencies = {
        "paymentprocessing::paymentbrokerapi"
    }
)
@SecondaryAdapter
package com.buildcomplete.examples.modularcqrsddd.paymentbrokerapifeign;

import org.jmolecules.architecture.hexagonal.SecondaryAdapter;
import org.springframework.modulith.ApplicationModule;