/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_9.sponge.event.player;

import dev.neuralnexus.taterapi.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.player.SimplePlayer;
import dev.neuralnexus.taterlib.v1_9.sponge.player.SpongePlayer;

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

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isMessageCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setMessageCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(players[0]);
    }

    /** {@inheritDoc} */
    @Override
    public String message() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().toPlain();
    }

    /** {@inheritDoc} */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> recipients() {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }
}
