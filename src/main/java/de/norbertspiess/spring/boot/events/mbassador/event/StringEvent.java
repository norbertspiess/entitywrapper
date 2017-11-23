package de.norbertspiess.spring.boot.events.mbassador.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class StringEvent extends AbstractEvent<String> {
    public StringEvent(String payload) {
        super(payload);
    }
}
