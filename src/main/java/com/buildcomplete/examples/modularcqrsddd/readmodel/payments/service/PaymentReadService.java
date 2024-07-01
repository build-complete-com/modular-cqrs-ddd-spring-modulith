package com.buildcomplete.examples.modularcqrsddd.readmodel.payments.service;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.BrokerPaymentIdAssignedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentCompletedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.events.PaymentStartedPortEvent;
import com.buildcomplete.examples.modularcqrsddd.readmodel.payments.repository.PaymentState;
import com.buildcomplete.examples.modularcqrsddd.readmodel.payments.repository.PaymentViewEntity;
import com.buildcomplete.examples.modularcqrsddd.readmodel.payments.repository.PaymentViewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentReadService {
    private final PaymentViewRepository repository;

    public List<PaymentViewEntity> getPayments() {
        return repository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
    @ApplicationModuleListener
    void handleEvent(PaymentStartedPortEvent event) {
        PaymentViewEntity entity = new PaymentViewEntity();
        entity.setPaymentId(event.getPaymentId());
        entity.setPaymentState(PaymentState.STARTED);
        repository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
    @ApplicationModuleListener
    void handleEvent(BrokerPaymentIdAssignedPortEvent event) {
        PaymentViewEntity entity = repository.findById(event.getPaymentId()).orElseThrow(() -> new IllegalStateException("Should exist"));
        entity.setPaymentState(PaymentState.BROKER_PAYMENT_ID_ASSIGNED);
        repository.save(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
    @ApplicationModuleListener
    void handleEvent(PaymentCompletedPortEvent event) {
        PaymentViewEntity entity = repository.findById(event.getPaymentId()).orElseThrow(() -> new IllegalStateException("Should exist"));
        entity.setPaymentState(PaymentState.COMPLETED);
        repository.save(entity);
    }
}
