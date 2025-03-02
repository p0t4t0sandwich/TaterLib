/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.event.player.PlayerLogoutEvent;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;

/** Bungee implementation of {@link PlayerLogoutEvent}. */
public class BungeePlayerLogoutEvent implements PlayerLogoutEvent {
    private final PlayerDisconnectEvent event;
    private String logoutMessage = "";

    public BungeePlayerLogoutEvent(PlayerDisconnectEvent event) {
        this.event = event;
    }

    @Override
    public ProxyPlayer player() {
        return WrapperRegistry.wrap(event.getPlayer());
    }

    @Override
    public String logoutMessage() {
        if (!logoutMessage.isEmpty()) {
            return logoutMessage;
        }
        return event.getPlayer() + " left the game";
    }

    @Override
    public void setLogoutMessage(String message) {
        logoutMessage = message;
    }
}
