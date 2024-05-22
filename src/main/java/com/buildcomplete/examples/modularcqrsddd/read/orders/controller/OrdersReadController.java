package com.buildcomplete.examples.modularcqrsddd.read.orders.controller;

import com.buildcomplete.examples.modularcqrsddd.read.orders.repository.OrderEntity;
import com.buildcomplete.examples.modularcqrsddd.read.orders.service.OrdersReadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrdersReadController {
  private final OrdersReadService ordersReadService;

  @GetMapping
  List<OrderEntity> listOrders() {
    return ordersReadService.getOrders();
  }
}
