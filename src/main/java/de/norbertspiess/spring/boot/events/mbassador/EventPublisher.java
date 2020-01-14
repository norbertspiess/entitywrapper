package de.norbertspiess.spring.boot.events.mbassador;

import de.norbertspiess.spring.boot.events.mbassador.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Slf4j
public class EventPublisher {

    private MBassador<MyEvent> eventBus;

    @Inject
    public EventPublisher(MBassador<MyEvent> eventBus) {
        this.eventBus = eventBus;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        MyEvent event = new MyEvent("message", "string");
        log.info("MBASSADOR-EVENT: posting string event {}", event);
        eventBus.publish(event);
    }

    @Scheduled(fixedDelay = 7500)
    public void sendInteger() {
        MyEvent event = new MyEvent(500, "integer");
        log.info("MBASSADOR-EVENT: posting integer event {}", event);
        eventBus.publish(event);
    }
}
