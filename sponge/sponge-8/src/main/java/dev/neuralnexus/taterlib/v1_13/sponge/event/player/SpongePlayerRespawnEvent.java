package dev.neuralnexus.taterlib.v1_13.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_13.sponge.player.SpongePlayer;
import dev.neuralnexus.taterlib.v1_13.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.world.Location;

import org.spongepowered.api.event.entity.living.player.RespawnPlayerEvent;

/** Sponge implementation of {@link PlayerRespawnEvent}. */
public class SpongePlayerRespawnEvent implements PlayerRespawnEvent {
    private final RespawnPlayerEvent.Recreate event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent.Recreate event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.entity());
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new SpongeLocation(event.entity().serverLocation());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBedSpawn() {
        return event.isBedSpawn();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
