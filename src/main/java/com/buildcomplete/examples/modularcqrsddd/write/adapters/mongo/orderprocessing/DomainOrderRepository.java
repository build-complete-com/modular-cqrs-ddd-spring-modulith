package com.buildcomplete.examples.modularcqrsddd.write.adapters.mongo.orderprocessing;

import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.Order;
import com.buildcomplete.examples.modularcqrsddd.domain.orderprocessing.OrderRepository;
import com.buildcomplete.examples.modularcqrsddd.domain.sharedkernel.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class DomainOrderRepository implements OrderRepository {
    private final OrderDocumentConverter converter;
    private final OrderMongoRepository repository;

    @Override
    public Optional<Order> findById(OrderId id) {
        return repository.findById(id.getValue()).map(converter::convert);
    }

    @Override
    public void save(Order aggregateRoot) {
        OrderDocument document = converter.convert(aggregateRoot);
        repository.save(document);
    }
}
