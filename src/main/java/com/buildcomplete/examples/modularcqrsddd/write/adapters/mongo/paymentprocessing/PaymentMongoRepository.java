package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.paymentprocessing;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

interface PaymentMongoRepository extends MongoRepository<PaymentDocument, UUID> {
    Optional<PaymentDocument> findByBrokerPaymentId(String brokerPaymentId);
}
