package de.norbertspiess.spring.boot.events.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Publisher {
    private ApplicationEventPublisher publisher;

    public Publisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 2500)
    public void publish() {
        double random = Math.random();
        log.info("SPRING-EVENT: publishing {}", random);
        publisher.publishEvent(new Event("" + random));
    }
}
