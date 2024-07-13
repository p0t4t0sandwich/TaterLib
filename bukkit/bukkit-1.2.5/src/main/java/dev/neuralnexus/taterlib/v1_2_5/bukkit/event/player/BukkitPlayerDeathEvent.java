/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_2_5.bukkit.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.entity.player.BukkitPlayer;
import dev.neuralnexus.taterlib.v1_2_5.bukkit.event.entity.BukkitEntityDeathEvent;

/** Bukkit implementation of {@link PlayerDeathEvent}. */
public class BukkitPlayerDeathEvent extends BukkitEntityDeathEvent implements PlayerDeathEvent {
    private final org.bukkit.event.entity.PlayerDeathEvent event;

    public BukkitPlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public Player player() {
        return new BukkitPlayer(event.getEntity());
    }

    @Override
    public String deathMessage() {
        return event.getDeathMessage();
    }

    @Override
    public void setDeathMessage(String deathMessage) {
        event.setDeathMessage(deathMessage);
    }

    @Override
    public boolean keepInventory() {
        return false;
    }

    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
