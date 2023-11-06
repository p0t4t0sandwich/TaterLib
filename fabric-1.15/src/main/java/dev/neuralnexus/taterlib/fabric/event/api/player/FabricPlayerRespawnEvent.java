package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Fabric implementation of {@link PlayerRespawnEvent}.
 */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements PlayerRespawnEvent {
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
        return new FabricLocation(((FabricPlayer) getPlayer()).getPlayer());
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
