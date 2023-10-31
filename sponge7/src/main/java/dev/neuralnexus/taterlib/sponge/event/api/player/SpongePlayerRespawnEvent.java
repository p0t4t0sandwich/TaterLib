package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;

/**
 * Sponge implementation of {@link PlayerRespawnEvent}.
 */
public class SpongePlayerRespawnEvent implements PlayerRespawnEvent {
    private final RespawnPlayerEvent event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer() {
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
