@ApplicationModule(
    displayName = "Payment Broker API Feign Client Adapter",
    allowedDependencies = {
        "paymentprocessing::domain"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentbrokerapifeign;

import org.springframework.modulith.ApplicationModule;