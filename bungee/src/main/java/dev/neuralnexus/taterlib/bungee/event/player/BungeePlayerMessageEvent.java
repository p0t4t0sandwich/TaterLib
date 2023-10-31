package dev.neuralnexus.taterlib.bungee.event.player;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Bungee implementation of {@link PlayerMessageEvent}.
 */
public class BungeePlayerMessageEvent implements PlayerMessageEvent {
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
    public Player getPlayer() {
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
    public Set<Player> recipients() {
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
    public void setRecipients(Set<Player> recipients) {}
}
