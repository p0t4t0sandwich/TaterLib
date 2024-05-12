package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.player;

import dev.neuralnexus.taterlib.v1_6_4.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.player.Player;

/** Bukkit implementation of {@link PlayerEvent}. */
public class BukkitPlayerEvent implements PlayerEvent {
    private final org.bukkit.event.player.PlayerEvent event;

    BukkitPlayerEvent(org.bukkit.event.player.PlayerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new BukkitPlayer(event.getPlayer());
    }
}
