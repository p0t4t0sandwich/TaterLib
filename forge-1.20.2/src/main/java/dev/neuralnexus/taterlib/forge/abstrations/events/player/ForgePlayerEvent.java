package dev.neuralnexus.taterlib.forge.abstrations.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.forge.abstrations.player.ForgePlayer;
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
