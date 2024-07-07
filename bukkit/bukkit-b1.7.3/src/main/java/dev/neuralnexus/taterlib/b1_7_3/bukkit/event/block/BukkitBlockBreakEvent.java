/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.b1_7_3.bukkit.event.block;

import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.player.BukkitPlayer;

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
