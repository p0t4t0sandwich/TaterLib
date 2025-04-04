/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.User;
import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.player.SpongePlayer;

import org.spongepowered.api.event.message.MessageEvent;

import java.util.Set;

/** Sponge implementation of {@link PlayerMessageEvent}. */
public class SpongePlayerMessageEvent implements PlayerMessageEvent {
    private final MessageEvent event;
    private final org.spongepowered.api.entity.living.player.Player[] players;
    private String message = "";

    public SpongePlayerMessageEvent(
            MessageEvent event, org.spongepowered.api.entity.living.player.Player[] players) {
        this.event = event;
        this.players = players;
    }

    @Override
    public boolean cancelled() {
        return event.isMessageCancelled();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        event.setMessageCancelled(cancelled);
    }

    @Override
    public Player player() {
        return new SpongePlayer(players[0]);
    }

    @Override
    public String message() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().toPlain();
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
