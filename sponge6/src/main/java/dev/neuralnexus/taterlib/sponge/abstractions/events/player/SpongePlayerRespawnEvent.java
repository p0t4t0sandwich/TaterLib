package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;

/**
 * Sponge implementation of {@link AbstractPlayerRespawnEvent}.
 */
public class SpongePlayerRespawnEvent implements AbstractPlayerRespawnEvent {
    private final RespawnPlayerEvent event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(getPlayer().getPosition(), new SpongeEntity(event.getTargetEntity()).getDimension());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
