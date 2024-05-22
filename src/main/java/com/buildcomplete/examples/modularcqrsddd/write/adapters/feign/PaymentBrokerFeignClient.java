package com.buildcomplete.examples.modularcqrsddd.write.adapters.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "PaymentBrokerApi", url = "${api.paymentbroker.url}")
public interface PaymentBrokerFeignClient {
    @RequestMapping(method = POST, value = "/payments")
    PaymentResource createPayment();
}
