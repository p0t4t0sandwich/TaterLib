package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Bukkit implementation of {@link AbstractPlayerLoginEvent}.
 */
public class BukkitPlayerLoginEvent extends BukkitPlayerEvent implements AbstractPlayerLoginEvent {
    private final PlayerJoinEvent event;

    public BukkitPlayerLoginEvent(PlayerJoinEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getLoginMessage() {
        return event.getJoinMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLoginMessage(String message) {
        event.setJoinMessage(message);
    }
}
