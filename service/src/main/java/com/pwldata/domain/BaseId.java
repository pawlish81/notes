package com.pwldata.domain;

import java.util.UUID;

public class BaseId {

    private UUID id;

    boolean isDeleted;

    public UUID getId() {
        return id;
    }

    public BaseId setId(UUID id) {
        this.id = id;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public BaseId setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "BaseId{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
