package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.neoforge.abstractions.player.NeoForgePlayer;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * NeoForge implementation of {@link AbstractPlayerMessageEvent}.
 */
public class NeoForgePlayerMessageEvent implements AbstractPlayerMessageEvent {
    private final ServerChatEvent event;
    private String message = "";

    public NeoForgePlayerMessageEvent(ServerChatEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCanceled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new NeoForgePlayer(event.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().getString();
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
        this.message = message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRecipients(Set<AbstractPlayer> recipients) {}
}
