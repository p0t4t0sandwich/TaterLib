package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;

import org.bukkit.event.player.PlayerQuitEvent;

/** Bukkit implementation of {@link PlayerLogoutEvent}. */
public class BukkitPlayerLogoutEvent extends BukkitPlayerEvent implements PlayerLogoutEvent {
    private final PlayerQuitEvent event;

    public BukkitPlayerLogoutEvent(PlayerQuitEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String getLogoutMessage() {
        return event.getQuitMessage();
    }

    /** {@inheritDoc} */
    @Override
    public void setLogoutMessage(String message) {
        event.setQuitMessage(message);
    }
}
