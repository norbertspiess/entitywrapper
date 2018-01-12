package de.norbertspiess.spring.boot.events.mbassador.listener;

import de.norbertspiess.spring.boot.events.mbassador.event.MyEvent;
import net.engio.mbassy.bus.MBassador;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

abstract class AbstractEventListener<EventType extends MyEvent> {

    private MBassador eventBus;

    AbstractEventListener(MBassador eventBus) {
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

    public void tryToHandleEvent(EventType event) {
        try{
            handleEvent(event);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handleEvent(EventType event);
}
