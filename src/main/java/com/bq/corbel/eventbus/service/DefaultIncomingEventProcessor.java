package com.bq.corbel.eventbus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bq.corbel.eventbus.Event;
import com.bq.corbel.eventbus.EventHandler;

/**
 * @author Alexander De Leon
 *
 */
public class DefaultIncomingEventProcessor implements IncomingEventProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultIncomingEventProcessor.class);

    private final EventBusRegistry registry;

    public DefaultIncomingEventProcessor(EventBusRegistry registry) {
        this.registry = registry;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void process(Event event) {
        for (EventHandler eventHandler : registry.getHandlers(event.getClass())) {
            try {
                eventHandler.handle(event);
            } catch (Exception e) {
                LOG.error("EventHandler raise exception. EventHandler: {} - Event: {} - Error: {}", eventHandler, event, e);
            }
        }

    }

}
