package dev.neuralnexus.taterlib.v1_19.vanilla.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.v1_19.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_19.vanilla.util.VanillaLocation;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
import java.util.UUID;

/** Vanilla implementation of {@link Entity}. */
public class VanillaEntity implements Entity {
    private final net.minecraft.world.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public VanillaEntity(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.world.entity.Entity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return entity.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(Component.nullToEmpty(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new VanillaLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.getX();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getY();
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return entity.getYRot();
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.getXRot();
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.getLevel().dimension().location().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        Optional<ResourceKey<Biome>> holder =
                entity.getLevel().getBiome(entity.blockPosition()).unwrap().left();
        return holder.map(biomeResourceKey -> biomeResourceKey.registry().toString()).orElse(null);
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = VanillaServer.getServer();
            if (server == null) return;
            ResourceKey<Level> dimension =
                    ResourceKey.create(
                            Registry.DIMENSION_REGISTRY,
                            new ResourceLocation(location.getWorld().split(":")[1]));
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
