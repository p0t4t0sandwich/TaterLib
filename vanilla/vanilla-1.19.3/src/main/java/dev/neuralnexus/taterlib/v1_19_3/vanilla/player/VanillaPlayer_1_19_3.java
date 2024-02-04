package dev.neuralnexus.taterlib.v1_19_3.vanilla.player;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

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

    /**
     * Constructor.
     *
     * @param player The player.
     * @param serverName The server name.
     */
    public VanillaPlayer_1_19_3(
            net.minecraft.world.entity.player.Player player, String serverName) {
        super(player, serverName);
        this.player = player;
    }

    /**
     * Gets the player
     *
     * @return The player
     */
    public net.minecraft.world.entity.player.Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        // TODO: Abstract world information
        ResourceKey<Level> dimension =
                ResourceKey.create(Registries.DIMENSION, new ResourceLocation(location.getWorld()));
        ((ServerPlayer) player)
                .setRespawnPosition(
                        dimension,
                        new BlockPos(
                                (int) location.getX(),
                                (int) location.getY(),
                                (int) location.getZ()),
                        0,
                        forced,
                        false);
    }
}
