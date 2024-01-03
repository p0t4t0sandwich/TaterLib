package dev.neuralnexus.taterlib.fabric.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.vanilla.util.VanillaLocation;

import net.minecraft.world.entity.player.Player;

/** Fabric implementation of {@link PlayerRespawnEvent}. */
public class FabricPlayerRespawnEvent extends FabricPlayerEvent implements PlayerRespawnEvent {
    private final boolean alive;
    private final Player player;

    public FabricPlayerRespawnEvent(Player player, boolean alive) {
        super(player);
        this.player = player;
        this.alive = alive;
    }

    /** {@inheritDoc} */
    @Override
    public Location getRespawnLocation() {
        return new VanillaLocation(player);
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
