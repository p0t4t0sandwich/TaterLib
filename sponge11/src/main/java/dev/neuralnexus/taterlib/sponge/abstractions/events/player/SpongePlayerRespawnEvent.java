package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.event.entity.living.player.RespawnPlayerEvent;

/**
 * Sponge implementation of {@link AbstractPlayerRespawnEvent}.
 */
public class SpongePlayerRespawnEvent implements AbstractPlayerRespawnEvent {
    private final RespawnPlayerEvent.Recreate event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent.Recreate event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.entity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(getPlayer().getPosition(), new SpongeEntity(event.entity()).getDimension());
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
