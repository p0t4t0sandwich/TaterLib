package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

/** Forge implementation of {@link PlayerEvent}. */
public class ForgePlayerEvent implements PlayerEvent {
    private final net.minecraftforge.event.entity.player.PlayerEvent event;

    public ForgePlayerEvent(net.minecraftforge.event.entity.player.PlayerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer(event.getPlayer());
    }
}
