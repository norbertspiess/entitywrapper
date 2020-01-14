package de.norbertspiess.spring.boot.events.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync // enables @Async for @EventListener
public class EventConfig {
}
