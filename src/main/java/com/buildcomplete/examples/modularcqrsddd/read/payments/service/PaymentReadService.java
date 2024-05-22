package com.buildcomplete.examples.modularcqrsddd.read.payments.service;

import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.BrokerPaymentIdAssignedEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.paymentprocessing.PaymentStartedEvent;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.PaymentCompletedEvent;
import com.buildcomplete.examples.modularcqrsddd.read.payments.repository.PaymentState;
import com.buildcomplete.examples.modularcqrsddd.read.payments.repository.PaymentViewEntity;
import com.buildcomplete.examples.modularcqrsddd.read.payments.repository.PaymentViewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentReadService {
    private final PaymentViewRepository repository;

    public List<PaymentViewEntity> getPayments() {
        return repository.findAll();
    }

    @Async
    @EventListener
    void handleEvent(PaymentStartedEvent event) {
        PaymentViewEntity entity = new PaymentViewEntity();
        entity.setPaymentId(event.getPaymentId().getValue());
        entity.setPaymentState(PaymentState.STARTED);
        repository.save(entity);
    }

    @Async
    @EventListener
    void handleEvent(BrokerPaymentIdAssignedEvent event) {
        PaymentViewEntity entity = repository.findById(event.getPaymentId().getValue()).orElseThrow(() -> new IllegalStateException("Should exist"));
        entity.setPaymentState(PaymentState.BROKER_PAYMENT_ID_ASSIGNED);
        repository.save(entity);
    }

    @Async
    @EventListener
    void handleEvent(PaymentCompletedEvent event) {
        PaymentViewEntity entity = repository.findById(event.getPaymentId().getValue()).orElseThrow(() -> new IllegalStateException("Should exist"));
        entity.setPaymentState(PaymentState.COMPLETED);
        repository.save(entity);
    }
}
