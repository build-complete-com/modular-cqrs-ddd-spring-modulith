package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

interface OrderDocumentMongoRepository extends MongoRepository<OrderDocument, UUID> {
}
