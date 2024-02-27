package dev.neuralnexus.taterlib.event.api;

import com.google.common.collect.ImmutableSet;

import dev.neuralnexus.taterlib.event.Event;

import java.util.Set;

/** Generic event system. */
public class GenericEvents {
    /** Called when the event is fired. */
    public static final EventManager<Event> EVENT = new EventManager<>(Event.class);

    /** Set of all event managers. */
    public static final Set<EventManager<? extends Event>> EVENTS =
            ImmutableSet.<EventManager<? extends Event>>builder()
                    .addAll(BlockEvents.events())
                    .addAll(CommandEvents.events())
                    .addAll(EntityEvents.events())
                    .addAll(NetworkEvents.events())
                    .addAll(PlayerEvents.events())
                    .addAll(PluginEvents.events())
                    .addAll(ServerEvents.events())
                    .build();

    /** Set up generic events. */
    public static void setup() {
        EVENTS.forEach(event -> event.register(EVENT::invoke));
    }
}
