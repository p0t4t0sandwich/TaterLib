package dev.neuralnexus.taterlib.v1_19.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

/** Vanilla implementation of {@link PlayerEvent}. */
public class VanillaPlayerEvent implements PlayerEvent {
    private final net.minecraft.world.entity.player.Player player;

    public VanillaPlayerEvent(net.minecraft.world.entity.player.Player player) {
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new VanillaPlayer(this.player);
    }
}
