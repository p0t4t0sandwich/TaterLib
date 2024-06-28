/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_2.bukkit.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;

import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerEvent;

/** Bukkit implementation of {@link PlayerAdvancementEvent}. */
public class BukkitPlayerAdvancementEvent extends BukkitPlayerEvent
        implements PlayerAdvancementEvent {
    public BukkitPlayerAdvancementEvent(PlayerEvent event) {
        super(event);
    }

    /** {@inheritDoc} */
    @Override
    public String advancement() {
        return null;
    }

    /** Bukkit implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends BukkitPlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        private final PlayerAdvancementDoneEvent event;

        public AdvancementFinished(PlayerAdvancementDoneEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public String advancement() {
            return event.getAdvancement().getKey().getKey();
        }
    }
}
