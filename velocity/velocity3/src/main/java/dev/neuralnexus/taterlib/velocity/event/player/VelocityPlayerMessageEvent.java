package dev.neuralnexus.taterlib.velocity.event.player;

import com.velocitypowered.api.event.player.PlayerChatEvent;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

import java.util.HashSet;
import java.util.Set;

/** Velocity implementation of {@link PlayerMessageEvent}. */
public class VelocityPlayerMessageEvent implements PlayerMessageEvent {
    private final PlayerChatEvent event;
    private String message = "";

    public VelocityPlayerMessageEvent(PlayerChatEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return event.getResult().isAllowed();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new VelocityPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        if (!message.isEmpty()) {
            return message;
        }
        return event.getMessage();
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
