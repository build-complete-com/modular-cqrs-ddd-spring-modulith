package com.buildcomplete.examples.modularcqrsddd.hexagoncore.application.domainframework;

import lombok.Getter;

public class DomainAggregateRoot<IdT> extends DomainEntity<IdT> {
    @Getter
    protected Long version;

    public DomainAggregateRoot(IdT id, Long version) {
        super(id);
        this.version = version;
    }
}
