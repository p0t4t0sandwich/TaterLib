package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.neoforge.abstractions.player.NeoForgePlayer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * NeoForge implementation of {@link AbstractPlayerEvent}.
 */
public class NeoForgePlayerEvent implements AbstractPlayerEvent {
    private final PlayerEvent event;

    public NeoForgePlayerEvent(PlayerEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new NeoForgePlayer(event.getEntity());
    }
}
