package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;

import java.util.Collections;
import java.util.Set;

/** World events. */
public class BlockEvents {
    /** Called when a block is broken. */
    public static final EventManager<PlayerBlockBreakEvent> PLAYER_BLOCK_BREAK =
            new EventManager<>(PlayerBlockBreakEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> getEvents() {
        return Collections.singleton(PLAYER_BLOCK_BREAK);
    }
}
