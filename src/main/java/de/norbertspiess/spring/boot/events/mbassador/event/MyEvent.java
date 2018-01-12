package de.norbertspiess.spring.boot.events.mbassador.event;

import lombok.Data;

@Data
public class MyEvent {
    private final Object payload;

    private final String type;
}
