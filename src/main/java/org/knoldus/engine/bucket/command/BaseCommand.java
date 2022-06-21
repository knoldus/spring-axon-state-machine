package org.knoldus.engine.bucket.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

public class BaseCommand<T> implements Serializable {

    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
