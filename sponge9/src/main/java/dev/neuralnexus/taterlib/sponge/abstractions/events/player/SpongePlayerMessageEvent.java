package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.PlayerChatEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * Sponge implementation of {@link AbstractPlayerMessageEvent}.
 */
public class SpongePlayerMessageEvent implements AbstractPlayerMessageEvent {
    private final PlayerChatEvent event;
    private final Player[] players;
    private String message = "";

    public SpongePlayerMessageEvent(PlayerChatEvent event, Player[] players) {
        this.event = event;
        this.players = players;
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
        return PlainTextComponentSerializer.plainText().serialize(event.message());
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
