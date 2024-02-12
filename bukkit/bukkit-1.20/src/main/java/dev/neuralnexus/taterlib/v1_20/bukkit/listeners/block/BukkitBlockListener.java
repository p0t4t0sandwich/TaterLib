package dev.neuralnexus.taterlib.v1_20.bukkit.listeners.block;

import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.v1_20.bukkit.adapters.BukkitAdapter;
import dev.neuralnexus.taterlib.v1_20.bukkit.event.BukkitCancellableEventWrapper;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.block.VanillaPlayerBlockBreakEvent;

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
                        BukkitAdapter.get().level(event.getBlock().getWorld()),
                        BukkitAdapter.get().player(event.getPlayer()),
                        BukkitAdapter.get().blockPos(event.getBlock()),
                        BukkitAdapter.get().blockState(event.getBlock()),
                        new BukkitCancellableEventWrapper<>(event)));
    }
}
