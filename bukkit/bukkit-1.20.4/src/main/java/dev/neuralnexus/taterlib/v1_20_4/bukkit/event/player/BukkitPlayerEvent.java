package dev.neuralnexus.taterlib.v1_20_4.bukkit.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.player.VanillaPlayer_1_20_2;
import dev.neuralnexus.taterlib.v1_20_4.bukkit.adapters.BukkitAdapter;

/** Bukkit implementation of {@link PlayerEvent}. */
public class BukkitPlayerEvent implements PlayerEvent {
    private final org.bukkit.event.player.PlayerEvent event;

    BukkitPlayerEvent(org.bukkit.event.player.PlayerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new VanillaPlayer_1_20_2(BukkitAdapter.get().player(event.getPlayer()));
    }
}
