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
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = VanillaServer.getServer();
            if (server == null) return;
            ResourceLocation resourceLocation;
            String[] resourceString = location.getWorld().split(":");
            if (resourceString.length != 1) {
                resourceLocation = new ResourceLocation(resourceString[0], resourceString[1]);
            } else {
                resourceLocation = new ResourceLocation(resourceString[0]);
            }
            ResourceKey<Level> dimension =
                    ResourceKey.create(Registries.DIMENSION, resourceLocation);
            ServerLevel serverLevel = server.getLevel(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel,
                        location.getX(),
                        location.getY(),
                        location.getZ(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel);
            }
        }
        entity.teleportTo(location.getX(), location.getY(), location.getZ());
    }
}
