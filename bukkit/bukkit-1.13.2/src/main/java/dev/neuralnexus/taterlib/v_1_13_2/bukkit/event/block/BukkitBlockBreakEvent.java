package dev.neuralnexus.taterlib.v_1_13_2.bukkit.event.block;

import dev.neuralnexus.taterlib.v_1_13_2.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;

/** Bukkit implementation of {@link PlayerBlockBreakEvent}. */
public class BukkitBlockBreakEvent extends BukkitBlockEvent implements PlayerBlockBreakEvent {
    private final org.bukkit.event.block.BlockBreakEvent event;

    public BukkitBlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }
}
