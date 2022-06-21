package org.knoldus.engine.bucket.event;

import java.io.Serializable;

public class BaseEvent<T> implements Serializable {
    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
