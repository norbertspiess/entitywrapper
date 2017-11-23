package de.norbertspiess.spring.boot.events.mbassador;

import de.norbertspiess.spring.boot.events.mbassador.event.AbstractEvent;
import de.norbertspiess.spring.boot.events.mbassador.event.IntegerEvent;
import de.norbertspiess.spring.boot.events.mbassador.event.StringEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventPublisher {

    private MBassador<AbstractEvent> eventBus;

    @Autowired
    public EventPublisher(MBassador<AbstractEvent> eventBus) {
        this.eventBus = eventBus;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        StringEvent event = new StringEvent("message");
        log.info("posting {}", event);
        eventBus.publishAsync(event);
    }

    @Scheduled(fixedDelay = 7500)
    public void sendInteger() {
        IntegerEvent event = new IntegerEvent(500);
        log.info("posting {}", event);
        eventBus.post(event).asynchronously();
    }
}
