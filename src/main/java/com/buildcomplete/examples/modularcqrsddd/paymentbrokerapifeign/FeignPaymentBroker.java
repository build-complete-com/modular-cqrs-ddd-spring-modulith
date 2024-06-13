package com.buildcomplete.examples.modularcqrsddd.paymentbrokerapifeign;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.domain.PaymentBroker;
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
