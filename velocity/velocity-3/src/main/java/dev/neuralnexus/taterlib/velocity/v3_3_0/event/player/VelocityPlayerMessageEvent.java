package dev.neuralnexus.taterlib.velocity.v3_3_0.event.player;

import com.velocitypowered.api.event.player.PlayerChatEvent;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.velocity.v3_3_0.player.VelocityPlayer;

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
    public boolean cancelled() {
        return event.getResult().isAllowed();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setResult(PlayerChatEvent.ChatResult.denied());
    }

    /** {@inheritDoc} */
    @Override
    public ProxyPlayer player() {
        return new VelocityPlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String message() {
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
    public Set<SimplePlayer> recipients() {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {
        // TODO: Chat recipients module
        throw new VersionFeatureNotSupportedException();
    }
}
