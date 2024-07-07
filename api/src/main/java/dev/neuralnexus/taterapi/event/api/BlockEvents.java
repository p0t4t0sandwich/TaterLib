/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.event.api;

import dev.neuralnexus.taterapi.event.Event;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;

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
    public static Set<EventManager<? extends Event>> events() {
        return Collections.singleton(PLAYER_BLOCK_BREAK);
    }
}
