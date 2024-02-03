package dev.neuralnexus.taterlib.v1_19.vanilla.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerRespawnEvent;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.util.VanillaLocation;

import net.minecraft.world.entity.player.Player;

/** Vanilla implementation of {@link PlayerRespawnEvent}. */
public class VanillaPlayerRespawnEvent extends VanillaPlayerEvent implements PlayerRespawnEvent {
    private final boolean alive;
    private final Player player;

    public VanillaPlayerRespawnEvent(Player player, boolean alive) {
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
