package de.norbertspiess.spring.boot.events.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Publisher {

    private final ApplicationEventPublisher publisher;

    @Scheduled(fixedDelay = 2500)
    public void publishDouble() {
        double randomDouble = Math.random();
        log.info("SPRING-EVENT: publishing double  {}", randomDouble);
        publisher.publishEvent(new Event<>(randomDouble));
    }

    @Scheduled(fixedDelay = 2750)
    public void publishInt() {
        int randomInt = (int) Math.floor(Math.random());
        log.info("SPRING-EVENT: publishing integer {}", randomInt);
        publisher.publishEvent(new Event<>(randomInt));
    }

    @Scheduled(fixedDelay = 3000)
    public void publishString() {
        String randomStr = "" + Math.random();
        log.info("SPRING-EVENT: publishing string {}", randomStr);
        publisher.publishEvent(new Event<>(randomStr));
    }
}
