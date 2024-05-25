package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.Payment;
import com.buildcomplete.examples.modularcqrsddd.domainsharedkernel.PaymentId;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessingdomain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class DomainPaymentRepository implements PaymentRepository {
    private final PaymentMongoRepository paymentMongoRepository;
    private final PaymentDocumentConverter converter;
    @Override
    public Optional<Payment> findById(PaymentId id) {
        return paymentMongoRepository.findById(id.getValue()).map(converter::convert);
    }

    @Override
    public void save(Payment aggregateRoot) {
        PaymentDocument document = converter.convert(aggregateRoot);
        paymentMongoRepository.save(document);
    }

    @Override
    public Optional<Payment> findByBrokerPaymentId(String brokerPaymentId) {
        return paymentMongoRepository.findByBrokerPaymentId(brokerPaymentId).map(converter::convert);
    }
}
