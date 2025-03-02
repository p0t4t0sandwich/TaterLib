/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.player.PlayerChatEvent;

import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

import java.util.Set;

/** Velocity implementation of {@link PlayerMessageEvent}. */
public class VelocityPlayerMessageEvent implements PlayerMessageEvent {
    private final PlayerChatEvent event;
    private String message = "";

    public VelocityPlayerMessageEvent(PlayerChatEvent event) {
        this.event = event;
    }

    @Override
    public boolean cancelled() {
        return event.getResult().isAllowed();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    @Override
    public String message() {
        if (!message.isEmpty()) {
            return message;
        }
        return event.getMessage();
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
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
