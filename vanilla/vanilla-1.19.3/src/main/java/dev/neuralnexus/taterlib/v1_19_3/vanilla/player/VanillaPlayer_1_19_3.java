package dev.neuralnexus.taterlib.v1_19_3.vanilla.player;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_19.vanilla.world.VanillaWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link Player}. */
public class VanillaPlayer_1_19_3 extends VanillaPlayer {
    private final net.minecraft.world.entity.player.Player player;

    /**
     * Constructor.
     *
     * @param player The player.
     */
    public VanillaPlayer_1_19_3(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayer) player)
                .setRespawnPosition(
                        ((VanillaWorld) location.world()).world().dimension(),
                        new BlockPos((int) location.x(), (int) location.y(), (int) location.z()),
                        0,
                        forced,
                        false);
    }
}
