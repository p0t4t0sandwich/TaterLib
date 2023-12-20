package dev.neuralnexus.taterlib.neoforge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.HashSet;
import java.util.Set;

/** NeoForge implementation of {@link PlayerMessageEvent}. */
public class NeoForgePlayerMessageEvent implements PlayerMessageEvent {
    private final ServerChatEvent event;
    private String message = "";

    public NeoForgePlayerMessageEvent(ServerChatEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new NeoForgePlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> recipients() {
        return new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<Player> recipients) {}
}
