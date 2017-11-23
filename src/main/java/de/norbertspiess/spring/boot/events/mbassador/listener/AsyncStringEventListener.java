package de.norbertspiess.spring.boot.events.mbassador.listener;

import de.norbertspiess.spring.boot.events.mbassador.event.StringEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncStringEventListener extends AbstractAsyncEventListener<StringEvent> {

    @Autowired
    public AsyncStringEventListener(MBassador eventBus) {
        super(eventBus);
    }

    public void handleEvent(StringEvent event) {
        log.info("got {}", event);
    }
}
