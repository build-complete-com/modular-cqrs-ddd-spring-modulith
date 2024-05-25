@ApplicationModule(
    displayName = "Payment Broker API Feign Client Adapter",
    allowedDependencies = {
        "paymentprocessingapplication",
        "paymentprocessingdomain"
    }
)
package com.buildcomplete.examples.modularcqrsddd.paymentbrokerapifeign;

import org.springframework.modulith.ApplicationModule;