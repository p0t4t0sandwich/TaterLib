package dev.neuralnexus.taterlib.bukkit.listeners.block;

import dev.neuralnexus.taterlib.bukkit.event.block.BukkitBlockBreakEvent;
import dev.neuralnexus.taterlib.common.event.api.BlockEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/** Listens for world events. */
public class BukkitBlockListener implements Listener {
    /**
     * Called when a block is broken.
     *
     * @param event The event.
     */
    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
        BlockEvents.BLOCK_BREAK.invoke(new BukkitBlockBreakEvent(event));
    }
}
