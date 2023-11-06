package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;
import org.spongepowered.api.event.entity.living.player.RespawnPlayerEvent;

/**
 * Sponge implementation of {@link PlayerRespawnEvent}.
 */
public class SpongePlayerRespawnEvent implements PlayerRespawnEvent {
    private final RespawnPlayerEvent.Recreate event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent.Recreate event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.entity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getRespawnLocation() {
        return new SpongeLocation(event.entity().location());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
