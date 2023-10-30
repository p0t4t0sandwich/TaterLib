package dev.neuralnexus.taterlib.velocity.abstractions.events.player;

import com.velocitypowered.api.event.player.PlayerChatEvent;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.velocity.abstractions.player.VelocityPlayer;

import java.util.HashSet;
import java.util.Set;

/**
 * Velocity implementation of {@link AbstractPlayerMessageEvent}.
 */
public class VelocityPlayerMessageEvent implements AbstractPlayerMessageEvent {
    private final PlayerChatEvent event;
    private String message = "";

    public VelocityPlayerMessageEvent(PlayerChatEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.getResult().isAllowed();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new VelocityPlayer(event.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        if (!message.isEmpty()) {
            return message;
        }
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
        this.message = message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRecipients(Set<AbstractPlayer> recipients) {}
}
