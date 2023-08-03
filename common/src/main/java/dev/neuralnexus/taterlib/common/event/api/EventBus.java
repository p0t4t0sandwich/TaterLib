package dev.neuralnexus.taterlib.common.event.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * These are really sketchy at the moment, don't use them.
 * Ex:
 * EventBus.INSTANCE.register(new TaterEvent<>(TaterPlayerLoginListener.class, (Consumer<Object[]>) TaterPlayerLoginListener::taterPlayerLogin));
 * ...
 * EventBus.INSTANCE.invoke(TaterPlayerLoginListener.class, new Object[]{taterPlayer});
 */
@Deprecated
public class EventBus {
    public static final EventBus INSTANCE = new EventBus();
    private static final HashMap<Class<?>, TaterEvent> events = new HashMap<>();

    public TaterEvent register(TaterEvent event) {
        Class<?> eventClass = event.getEventClass();
        if (!events.containsKey(eventClass)) {
            events.put(eventClass, event);
        } else {
            for (Consumer listener : (ArrayList<Consumer>) event.getListeners()) {
                events.get(eventClass).addListener(listener);
            }
        }
        return event;
    }

    public void invoke(Class<?> eventClass, Object event) {
        if (events.containsKey(eventClass)) {
            events.get(eventClass).call(event);
        }
    }

    public void invoke(Class<?> eventClass, Object[] args) {
        if (events.containsKey(eventClass)) {
            events.get(eventClass).call(args);
        }
    }
}
