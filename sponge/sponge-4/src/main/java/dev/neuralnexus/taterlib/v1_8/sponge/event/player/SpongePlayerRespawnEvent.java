package dev.neuralnexus.taterlib.v1_8.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_8.sponge.player.SpongePlayer;
import dev.neuralnexus.taterlib.v1_8.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.world.Location;

import org.spongepowered.api.event.entity.living.humanoid.player.RespawnPlayerEvent;

/** Sponge implementation of {@link PlayerRespawnEvent}. */
public class SpongePlayerRespawnEvent implements PlayerRespawnEvent {
    private final RespawnPlayerEvent event;

    public SpongePlayerRespawnEvent(RespawnPlayerEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new SpongeLocation(event.getTargetEntity().getLocation());
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
