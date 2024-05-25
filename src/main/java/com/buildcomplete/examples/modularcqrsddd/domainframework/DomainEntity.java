package com.buildcomplete.examples.modularcqrsddd.domainframework;

import lombok.Getter;

import java.util.Objects;

public class DomainEntity<IdT> {

    @Getter
    protected final IdT id;

    public DomainEntity(IdT id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntity that = (DomainEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
