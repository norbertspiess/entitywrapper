package de.norbertspiess.spring.boot.events.mbassador.listener;

import de.norbertspiess.spring.boot.events.mbassador.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Filter;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.IMessageFilter;
import net.engio.mbassy.subscription.SubscriptionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StringEventListener extends AbstractEventListener<MyEvent> {

    @Autowired
    public StringEventListener(MBassador eventBus) {
        super(eventBus);
    }

    @Override
    @Handler(filters = @Filter(EventFilter.class))
    public void tryToHandleEvent(MyEvent event) {
        super.tryToHandleEvent(event);
    }

    @Override
    public void handleEvent(MyEvent event) {
        log.info("got {}", event);
    }

    public static class EventFilter implements IMessageFilter<MyEvent> {
        @Override
        public boolean accepts(MyEvent event, SubscriptionContext subscriptionContext) {
            return event.getType().equalsIgnoreCase("string");
        }
    }
}
