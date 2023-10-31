package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.neoforge.abstractions.entity.NeoForgeEntity;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * NeoForge implementation of {@link AbstractPlayerRespawnEvent}.
 */
public class NeoForgePlayerRespawnEvent extends NeoForgePlayerEvent implements AbstractPlayerRespawnEvent {
    private final PlayerEvent.PlayerRespawnEvent event;

    public NeoForgePlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(getPlayer().getPosition(), new NeoForgeEntity(event.getEntity()).getDimension());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
