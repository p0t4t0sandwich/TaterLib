package dev.neuralnexus.taterlib.bukkit.abstractions.events.player;

import dev.neuralnexus.taterlib.bukkit.abstractions.player.BukkitPlayer;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Bukkit implementation of {@link AbstractPlayerMessageEvent}.
 */
public class BukkitPlayerMessageEvent extends BukkitPlayerEvent implements AbstractPlayerMessageEvent {
    private final AsyncPlayerChatEvent event;

    public BukkitPlayerMessageEvent(AsyncPlayerChatEvent event) {
        super(event);
        this.event = event;
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
    public String getMessage() {
        return event.getMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<AbstractPlayer> recipients() {
        return event.getRecipients().stream().map(BukkitPlayer::new).collect(Collectors.toSet());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMessage(String message) {
        event.setMessage(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRecipients(Set<AbstractPlayer> recipients) {
        event.getRecipients().clear();
        event.getRecipients().addAll(recipients.stream().map(player -> ((BukkitPlayer) player).getPlayer()).collect(Collectors.toSet()));
    }
}
