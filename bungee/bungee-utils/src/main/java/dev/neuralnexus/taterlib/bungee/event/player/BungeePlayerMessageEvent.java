/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterapi.WrapperRegistry;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.Set;

/** Bungee implementation of {@link PlayerMessageEvent}. */
public class BungeePlayerMessageEvent implements PlayerMessageEvent {
    private final ChatEvent event;

    public BungeePlayerMessageEvent(ChatEvent event) {
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    @Override
    public ProxyPlayer player() {
        return WrapperRegistry.wrap((ProxiedPlayer) event.getSender());
    }

    @Override
    public String message() {
        return this.event.getMessage();
    }

    @Override
    public void setMessage(String message) {
        this.event.setMessage(message);
    }

    @Override
    public Set<User> recipients() {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setRecipients(Set<User> recipients) {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }
}
