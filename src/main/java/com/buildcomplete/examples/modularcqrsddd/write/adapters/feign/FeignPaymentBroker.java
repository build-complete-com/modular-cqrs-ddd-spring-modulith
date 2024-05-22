package com.buildcomplete.examples.modularcqrsddd.write.adapters.feign;

import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentBroker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FeignPaymentBroker implements PaymentBroker {
    private final PaymentBrokerFeignClient client;
    @Override
    public String getBrokerPaymentId() {
        return client.createPayment().getId().toString();
    }
}
