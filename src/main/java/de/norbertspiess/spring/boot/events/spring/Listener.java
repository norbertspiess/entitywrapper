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
    public void listen(Event event) {
        log.info("SPRING-EVENT: got event {}", event.getMsg());
    }
}
