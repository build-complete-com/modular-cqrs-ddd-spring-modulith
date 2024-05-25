package com.buildcomplete.examples.modularcqrsddd.domainframework;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DomainAggregateChange<DomainAggregateT extends DomainAggregateRoot> {
    private DomainAggregateT changedAggregate;
    private List<DomainEvent> domainEvents;
}
