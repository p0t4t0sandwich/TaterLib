package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bukkit implementation of {@link PlayerMessageEvent}.
 */
public class BukkitPlayerMessageEvent extends BukkitPlayerEvent implements PlayerMessageEvent {
    private final AsyncPlayerChatEvent event;

    public BukkitPlayerMessageEvent(AsyncPlayerChatEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return event.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Player> recipients() {
        return event.getRecipients().stream().map(BukkitPlayer::new).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(String message) {
        event.setMessage(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecipients(Set<Player> recipients) {
        event.getRecipients().clear();
        event.getRecipients().addAll(recipients.stream().map(player -> ((BukkitPlayer) player).getPlayer()).collect(Collectors.toSet()));
    }
}
