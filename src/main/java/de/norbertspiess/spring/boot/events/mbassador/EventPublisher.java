package de.norbertspiess.spring.boot.events.mbassador;

import de.norbertspiess.spring.boot.events.mbassador.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventPublisher {

    private MBassador<MyEvent> eventBus;

    @Autowired
    public EventPublisher(MBassador<MyEvent> eventBus) {
        this.eventBus = eventBus;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        MyEvent event = new MyEvent("message", "string");
        log.info("posting string event {}", event);
        eventBus.publish(event);
    }

    @Scheduled(fixedDelay = 7500)
    public void sendInteger() {
        MyEvent event = new MyEvent(500, "integer");
        log.info("posting integer event {}", event);
        eventBus.publish(event);
    }
}
