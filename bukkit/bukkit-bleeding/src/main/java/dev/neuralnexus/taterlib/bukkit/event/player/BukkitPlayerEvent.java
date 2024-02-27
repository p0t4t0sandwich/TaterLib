package dev.neuralnexus.taterlib.bukkit.event.player;

import dev.neuralnexus.taterlib.bukkit.adapters.BukkitAdapters;
import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

/** Bukkit implementation of {@link PlayerEvent}. */
public class BukkitPlayerEvent implements PlayerEvent {
    private final org.bukkit.event.player.PlayerEvent event;

    BukkitPlayerEvent(org.bukkit.event.player.PlayerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new VanillaPlayer(BukkitAdapters.player(event.getPlayer()));
    }
}
