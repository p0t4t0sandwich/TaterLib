package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.player.SimplePlayer;

import org.bukkit.event.player.PlayerChatEvent;

import java.util.Set;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link PlayerMessageEvent}. */
public class BukkitPlayerMessageEvent extends BukkitPlayerEvent implements PlayerMessageEvent {
    private final PlayerChatEvent event;

    public BukkitPlayerMessageEvent(PlayerChatEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String message() {
        return event.getMessage();
    }

    /** {@inheritDoc} */
    @Override
    public void setMessage(String message) {
        event.setMessage(message);
    }

    /** {@inheritDoc} */
    @Override
    public Set<SimplePlayer> recipients() {
        return event.getRecipients().stream().map(BukkitPlayer::new).collect(Collectors.toSet());
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<SimplePlayer> recipients) {
        event.getRecipients().clear();
        event.getRecipients()
                .addAll(
                        recipients.stream()
                                .map(player -> ((BukkitPlayer) player).player())
                                .collect(Collectors.toSet()));
    }
}
