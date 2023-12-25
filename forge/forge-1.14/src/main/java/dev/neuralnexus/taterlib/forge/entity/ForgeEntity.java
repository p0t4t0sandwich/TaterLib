package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
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
        return entity.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
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
        return entity.getPosition().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getPosition().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getPosition().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return entity.getPitchYaw().x;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.getPitchYaw().y;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.world.dimension.getType().getRegistryName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry =
                entity.world.getBiome(entity.getPosition()).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
            if (server == null) return;
            DimensionType dimension =
                    DimensionType.byName(new ResourceLocation(location.getWorld().split(":")[1]));
            if (dimension == null) return;
            ServerWorld serverLevel = server.getWorld(dimension);
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity)
                        .teleport(
                                serverLevel,
                                location.getX(),
                                location.getY(),
                                location.getZ(),
                                entity.rotationYaw,
                                entity.rotationPitch);
                return;
            } else {
                entity.changeDimension(dimension);
            }
        }
        ((LivingEntity) entity)
                .attemptTeleport(location.getX(), location.getY(), location.getZ(), false);
    }
}
