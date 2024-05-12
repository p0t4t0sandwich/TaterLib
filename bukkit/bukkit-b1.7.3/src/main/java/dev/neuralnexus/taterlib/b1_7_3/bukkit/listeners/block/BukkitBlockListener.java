package dev.neuralnexus.taterlib.b1_7_3.bukkit.listeners.block;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.event.block.BukkitBlockBreakEvent;
import dev.neuralnexus.taterlib.event.api.BlockEvents;

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
