package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.MessageEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Sponge implementation of {@link AbstractPlayerMessageEvent}.
 */
public class SpongePlayerMessageEvent implements AbstractPlayerMessageEvent {
    private final MessageEvent event;
    private final Player[] players;
    private String message = "";

    public SpongePlayerMessageEvent(MessageEvent event, Player[] players) {
        this.event = event;
        this.players = players;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isMessageCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setMessageCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(players[0]);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        if (!this.message.isEmpty()) {
            return this.message;
        }
        return event.getMessage().toPlain();
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
