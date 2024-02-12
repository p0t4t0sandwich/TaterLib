package dev.neuralnexus.taterlib.v1_19_3.vanilla.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.entity.VanillaEntity;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

/** Vanilla implementation of {@link Entity}. */
public class VanillaEntity_1_19_3 extends VanillaEntity {
    private final net.minecraft.world.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public VanillaEntity_1_19_3(net.minecraft.world.entity.Entity entity) {
        super(entity);
        this.entity = entity;
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
            MinecraftServer server = VanillaServer.server();
            if (server == null) return;
            ResourceKey<Level> dimension =
                    ResourceKey.create(
                            Registries.DIMENSION, new ResourceLocation(location.world()));
            ServerLevel serverLevel = server.getLevel(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel,
                        location.x(),
                        location.y(),
                        location.z(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel);
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
