package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.player.SimplePlayer;

import net.minecraftforge.event.ServerChatEvent;

import java.util.HashSet;
import java.util.Set;

/** Forge implementation of {@link PlayerMessageEvent}. */
public class ForgePlayerMessageEvent implements PlayerMessageEvent {
    private final ServerChatEvent event;
    private String message = "";

    public ForgePlayerMessageEvent(ServerChatEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCanceled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCanceled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public String message() {
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
    public Set<SimplePlayer> recipients() {
        return new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {}
}
