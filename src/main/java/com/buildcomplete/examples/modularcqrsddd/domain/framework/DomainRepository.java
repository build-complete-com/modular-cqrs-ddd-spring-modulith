package com.buildcomplete.examples.modularcqrsddd.domain.framework;

import java.util.Optional;

public interface DomainRepository<AggregateRootT extends DomainAggregateRoot<IdT>, IdT> {
    Optional<AggregateRootT> findById(IdT id);

    void save(AggregateRootT aggregateRoot);
}
