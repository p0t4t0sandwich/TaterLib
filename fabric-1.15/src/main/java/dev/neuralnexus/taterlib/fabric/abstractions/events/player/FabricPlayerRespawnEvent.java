package dev.neuralnexus.taterlib.fabric.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import dev.neuralnexus.taterlib.fabric.abstractions.util.FabricConversions;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Fabric implementation of {@link AbstractPlayerRespawnEvent}.
 */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements AbstractPlayerRespawnEvent {
    private final boolean alive;
    private final PlayerEntity player;

    public FabricPlayerRespawnEvent(PlayerEntity player, boolean alive) {
        super(player);
        this.player = player;
        this.alive = alive;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getRespawnLocation() {
        return new Location(FabricConversions.positionFromVector(player.getPos()), player.getYaw(0.0F), player.getPitch(0.0F), player.getEntityWorld().getDimension().getType().toString());
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
