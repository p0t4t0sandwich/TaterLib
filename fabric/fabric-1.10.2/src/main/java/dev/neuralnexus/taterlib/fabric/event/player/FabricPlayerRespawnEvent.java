package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.player.PlayerEntity;

/** Fabric implementation of {@link PlayerRespawnEvent}. */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements PlayerRespawnEvent {
    private final boolean alive;
    private final PlayerEntity player;

    public FabricPlayerRespawnEvent(PlayerEntity player, int dimension, boolean alive) {
        super(player);
        this.player = player;
        this.alive = alive;
    }

    /** {@inheritDoc} */
    @Override
    public Location respawnLocation() {
        return new FabricLocation(((FabricPlayer) player()).player());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isBedSpawn() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAnchorSpawn() {
        return false;
    }
}
