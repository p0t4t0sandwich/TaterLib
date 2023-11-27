package dev.neuralnexus.taterlib.forge.event.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

/**
 * Forge implementation of {@link PlayerEvent}.
 */
public class ForgePlayerEvent implements PlayerEvent {
    private final net.minecraftforge.fml.common.gameevent.PlayerEvent event;

    public ForgePlayerEvent(net.minecraftforge.fml.common.gameevent.PlayerEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new ForgePlayer(event.getPlayer());
    }
}
