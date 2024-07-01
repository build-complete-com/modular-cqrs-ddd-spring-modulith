package com.buildcomplete.examples.modularcqrsddd.paymentprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDto;
import com.buildcomplete.examples.modularcqrsddd.paymentprocessing.ports.repository.PaymentDtoRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MongoDbPaymentDtoRepository implements PaymentDtoRepository {
    private final PaymentMongoRepository paymentMongoRepository;
    private final PaymentDocumentConverter converter;
    @Override
    public Optional<PaymentDto> findById(UUID id) {
        return paymentMongoRepository.findById(id).map(converter::convert);
    }

    @Override
    public void save(PaymentDto paymentDto) {
        PaymentDocument document = converter.convert(paymentDto);
        paymentMongoRepository.save(document);
    }

    @Override
    public Optional<PaymentDto> findByBrokerPaymentId(String brokerPaymentId) {
        return paymentMongoRepository.findByBrokerPaymentId(brokerPaymentId).map(converter::convert);
    }
}
