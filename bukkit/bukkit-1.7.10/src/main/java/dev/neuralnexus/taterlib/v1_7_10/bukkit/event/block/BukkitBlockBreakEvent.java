/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.bukkit.event.block;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_7_10.bukkit.entity.player.BukkitPlayer;

/** Bukkit implementation of {@link PlayerBlockBreakEvent}. */
public class BukkitBlockBreakEvent extends BukkitBlockEvent implements PlayerBlockBreakEvent {
    private final org.bukkit.event.block.BlockBreakEvent event;

    public BukkitBlockBreakEvent(org.bukkit.event.block.BlockBreakEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }
}
