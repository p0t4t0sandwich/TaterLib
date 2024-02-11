package dev.neuralnexus.taterlib.bukkit.listeners.block;

import dev.neuralnexus.taterlib.bukkit.adapters.BukkitAdapters;
import dev.neuralnexus.taterlib.bukkit.event.BukkitCancellableEventWrapper;
import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.vanilla.event.block.VanillaPlayerBlockBreakEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/** Listens for world events. */
public class BukkitBlockListener implements Listener {
    /**
     * Called when a block is broken.
     *
     * @param event The event.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        BlockEvents.PLAYER_BLOCK_BREAK.invoke(
                new VanillaPlayerBlockBreakEvent(
                        BukkitAdapters.getLevel(event.getBlock().getWorld()),
                        BukkitAdapters.getPlayer(event.getPlayer()),
                        BukkitAdapters.getBlockPos(event.getBlock()),
                        BukkitAdapters.getBlockState(event.getBlock()),
                        new BukkitCancellableEventWrapper<>(event)));
    }
}
