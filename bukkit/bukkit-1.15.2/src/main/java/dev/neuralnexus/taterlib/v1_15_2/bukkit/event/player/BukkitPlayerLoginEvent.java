/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerLoginEvent;

import org.bukkit.event.player.PlayerJoinEvent;

/** Bukkit implementation of {@link PlayerLoginEvent}. */
public class BukkitPlayerLoginEvent extends BukkitPlayerEvent implements PlayerLoginEvent {
    private final PlayerJoinEvent event;

    public BukkitPlayerLoginEvent(PlayerJoinEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public String loginMessage() {
        return event.getJoinMessage();
    }

    @Override
    public void setLoginMessage(String message) {
        event.setJoinMessage(message);
    }
}
