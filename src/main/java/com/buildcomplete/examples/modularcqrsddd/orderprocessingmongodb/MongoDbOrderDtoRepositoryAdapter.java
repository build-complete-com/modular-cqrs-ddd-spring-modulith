package com.buildcomplete.examples.modularcqrsddd.orderprocessingmongodb;

import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDto;
import com.buildcomplete.examples.modularcqrsddd.orderprocessing.ports.repository.OrderDtoRepositoryPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class MongoDbOrderDtoRepositoryAdapter implements OrderDtoRepositoryPort {
    private final OrderDocumentConverter converter;
    private final OrderDocumentMongoRepository repository;

    @Override
    public Optional<OrderDto> findById(UUID id) {
        return repository.findById(id).map(converter::convert);
    }

    @Override
    public void save(OrderDto order) {
        OrderDocument document = converter.convert(order);
        repository.save(document);
    }
}
