package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraftforge.event.ServerChatEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Forge implementation of {@link PlayerMessageEvent}.
 */
public class ForgePlayerMessageEvent implements PlayerMessageEvent {
    private final ServerChatEvent event;
    private String message = "";

    public ForgePlayerMessageEvent(ServerChatEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new ForgePlayer(event.getPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> recipients() {
        return new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecipients(Set<Player> recipients) {}
}
