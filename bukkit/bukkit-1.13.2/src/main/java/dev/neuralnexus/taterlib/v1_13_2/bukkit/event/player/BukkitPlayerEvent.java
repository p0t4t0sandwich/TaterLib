/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerEvent;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.entity.player.BukkitPlayer;

/** Bukkit implementation of {@link PlayerEvent}. */
public class BukkitPlayerEvent implements PlayerEvent {
    private final org.bukkit.event.player.PlayerEvent event;

    BukkitPlayerEvent(org.bukkit.event.player.PlayerEvent event) {
        this.event = event;
    }

    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }
}
