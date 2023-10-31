package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Forge implementation of {@link AbstractPlayerEvent}.
 */
public class ForgePlayerEvent implements AbstractPlayerEvent {
    private final PlayerEvent event;

    public ForgePlayerEvent(PlayerEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new ForgePlayer(event.getEntity());
    }
}
