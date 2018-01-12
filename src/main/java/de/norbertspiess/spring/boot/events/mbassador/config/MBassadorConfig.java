package de.norbertspiess.spring.boot.events.mbassador.config;

import de.norbertspiess.spring.boot.events.mbassador.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MBassadorConfig {
    @Bean
    public MBassador<MyEvent> eventBus() {
        return new MBassador<>(error -> {
            log.error("", error.getCause());
        });
    }
}
