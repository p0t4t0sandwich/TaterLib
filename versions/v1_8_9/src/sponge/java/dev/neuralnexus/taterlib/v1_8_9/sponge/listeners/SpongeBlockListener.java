/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_8_9.sponge.event.block.SpongeBlockBreakEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

/** Listens for entity events. */
public class SpongeBlockListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The entity damage event
     */
    @Listener
    public void onBlockBreak(ChangeBlockEvent.Pre event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(new SpongeBlockBreakEvent(event));
    }
}
