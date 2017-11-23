package de.norbertspiess.spring.boot.events.mbassador.event;

import lombok.Data;

@Data
public class AbstractEvent<T> {
    protected final T payload;
}
