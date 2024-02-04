package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.UUID;

/** Forge implementation of {@link Entity}. */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     *
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity getEntity() {
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
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new StringTextComponent(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new ForgeLocation(entity);
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
        return entity.xRot;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.yRot;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.level.dimension().location().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.level.getBiome(entity.blockPosition()).toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) return;
            RegistryKey<World> dimension =
                    RegistryKey.create(
                            Registry.DIMENSION_REGISTRY,
                            new ResourceLocation(location.getWorld().split(":")[1]));
            ServerWorld serverLevel = server.getLevel(dimension);
            if (serverLevel == null) return;
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity)
                        .teleportTo(
                                serverLevel,
                                location.getX(),
                                location.getY(),
                                location.getZ(),
                                entity.getRotationVector().y,
                                entity.getRotationVector().x);
                return;
            } else {
                entity.changeDimension(serverLevel);
            }
        }
        entity.teleportTo(location.getX(), location.getY(), location.getZ());
    }
}
