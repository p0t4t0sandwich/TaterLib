/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_19.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.v1_19.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.message.PlayerChatEvent;

import java.util.Set;

/** Sponge implementation of {@link PlayerMessageEvent}. */
public class SpongePlayerMessageEvent implements PlayerMessageEvent {
    private final PlayerChatEvent.Submit event;
    private final org.spongepowered.api.entity.living.player.Player[] players;
    private String message = "";

    public SpongePlayerMessageEvent(
            PlayerChatEvent.Submit event,
            org.spongepowered.api.entity.living.player.Player[] players) {
        this.event = event;
        this.players = players;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
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
        return PlainTextComponentSerializer.plainText().serialize(event.message());
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
