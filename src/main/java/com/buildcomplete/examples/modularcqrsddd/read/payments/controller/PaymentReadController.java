package com.buildcomplete.examples.modularcqrsddd.read.payments.controller;

import com.buildcomplete.examples.modularcqrsddd.read.payments.repository.PaymentViewEntity;
import com.buildcomplete.examples.modularcqrsddd.read.payments.service.PaymentReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentReadController {
  private final PaymentReadService paymentReadService;

  @GetMapping
  List<PaymentViewEntity> listOrders() {
    return paymentReadService.getPayments();
  }
}
