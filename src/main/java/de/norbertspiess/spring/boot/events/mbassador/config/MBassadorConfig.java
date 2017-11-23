package de.norbertspiess.spring.boot.events.mbassador.config;

import de.norbertspiess.spring.boot.events.mbassador.event.AbstractEvent;
import net.engio.mbassy.bus.MBassador;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MBassadorConfig {
    @Bean
    public MBassador<AbstractEvent> eventBus() {
        return new MBassador<>();
    }
}
