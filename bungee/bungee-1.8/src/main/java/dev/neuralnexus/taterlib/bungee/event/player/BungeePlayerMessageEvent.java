package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.Set;

/** Bungee implementation of {@link PlayerMessageEvent}. */
public class BungeePlayerMessageEvent implements PlayerMessageEvent {
    private final ChatEvent event;

    public BungeePlayerMessageEvent(ChatEvent event) {
        this.event = event;
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
    public ProxyPlayer player() {
        return new BungeePlayer((ProxiedPlayer) event.getSender());
    }

    /** {@inheritDoc} */
    @Override
    public String message() {
        return event.getMessage();
    }

    /** {@inheritDoc} */
    @Override
    public void setMessage(String message) {
        event.setMessage(message);
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
