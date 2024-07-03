package com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework;

import java.util.Optional;

public interface DomainRepository<AggregateRootT extends DomainAggregateRoot<IdT>, IdT> {
    Optional<AggregateRootT> findById(IdT id);

    void save(AggregateRootT aggregateRoot);
}
