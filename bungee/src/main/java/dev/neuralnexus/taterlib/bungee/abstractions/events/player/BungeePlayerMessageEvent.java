package dev.neuralnexus.taterlib.bungee.abstractions.events.player;

import dev.neuralnexus.taterlib.bungee.abstractions.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Bungee implementation of {@link AbstractPlayerMessageEvent}.
 */
public class BungeePlayerMessageEvent implements AbstractPlayerMessageEvent {
    private final ChatEvent event;

    public BungeePlayerMessageEvent(ChatEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new BungeePlayer((ProxiedPlayer) event.getSender());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return event.getMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<AbstractPlayer> recipients() {
        return new HashSet<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMessage(String message) {
        event.setMessage(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRecipients(Set<AbstractPlayer> recipients) {}
}
