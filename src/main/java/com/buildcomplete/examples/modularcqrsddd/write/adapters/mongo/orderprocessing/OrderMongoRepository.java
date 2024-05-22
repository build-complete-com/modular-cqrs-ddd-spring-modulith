package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.orderprocessing;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

interface OrderMongoRepository extends MongoRepository<OrderDocument, UUID> {
}
