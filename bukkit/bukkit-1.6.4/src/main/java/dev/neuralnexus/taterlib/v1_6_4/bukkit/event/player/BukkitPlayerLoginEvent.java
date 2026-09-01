/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.player;

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
