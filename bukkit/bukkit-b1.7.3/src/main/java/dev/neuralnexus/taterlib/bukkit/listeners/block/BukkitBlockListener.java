package dev.neuralnexus.taterlib.bukkit.listeners.block;

import dev.neuralnexus.taterlib.bukkit.event.block.BukkitBlockBreakEvent;
import dev.neuralnexus.taterlib.common.event.api.BlockEvents;
import org.bukkit.event.block.BlockListener;

/**
 * Listens for world events.
 */
public class BukkitBlockListener extends BlockListener {
    /**
     * Called when a block is broken.
     * @param event The event.
     */
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        BlockEvents.BLOCK_BREAK.invoke(new BukkitBlockBreakEvent(event));
    }
}
