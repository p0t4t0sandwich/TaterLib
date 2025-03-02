/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.block.BukkitBlockBreakEvent;

import org.bukkit.event.block.BlockListener;

/** Listens for world events. */
public class BukkitBlockListener extends BlockListener {
    /**
     * Called when a block is broken.
     *
     * @param event The event.
     */
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(new BukkitBlockBreakEvent(event));
    }
}
