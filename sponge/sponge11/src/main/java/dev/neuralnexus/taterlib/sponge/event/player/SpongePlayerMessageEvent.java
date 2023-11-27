package dev.neuralnexus.taterlib.sponge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.message.PlayerChatEvent;

import java.util.HashSet;
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
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(players[0]);
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
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
    public Set<Player> recipients() {
        return new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<Player> recipients) {}
}
