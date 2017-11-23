package de.norbertspiess.spring.boot.events.mbassador.listener;

import de.norbertspiess.spring.boot.events.mbassador.event.AbstractEvent;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

abstract class AbstractAsyncEventListener<EventType extends AbstractEvent> {

    private MBassador eventBus;

    AbstractAsyncEventListener(MBassador eventBus) {
        this.eventBus = eventBus;
    }

    @PostConstruct
    public void init() {
        eventBus.subscribe(this);
    }

    @PreDestroy
    public void tearDown() {
        eventBus.unsubscribe(this);
    }

    @Handler(delivery = Invoke.Asynchronously)
    public abstract void handleEvent(EventType event);
}
