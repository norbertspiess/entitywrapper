package de.norbertspiess.spring.boot.events.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Listener {

    @Async
    @EventListener
    public void listen(Event<?> event) {
        log.info("SPRING-EVENT: got any event: {}", event.getContent());
    }

    @Async
    @EventListener
    public void listenDouble(Event<Double> event) {
        log.info("SPRING-EVENT: got double event: {}", event.getContent());
    }

    @Async
    @EventListener
    public void listenInt(Event<Integer> event) {
        log.info("SPRING-EVENT: got int event: {}", event.getContent());
    }

    @Async
    @EventListener
    public void listenString(Event<String> event) {
        log.info("SPRING-EVENT: got string event: {}", event.getContent());
    }
}
