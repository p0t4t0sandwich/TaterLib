package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;

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
        entity.setDead();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (!entity.hasCustomName()) return null;
        return entity.getCustomNameTag();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomNameTag(name);
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
        return DimensionType.getById(entity.dimension).getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry =
                entity.world
                        .getBiome(
                                entity.world
                                        .getChunk(entity.getPosition())
                                        .getPos()
                                        .getBlock((int) getX(), (int) getY(), (int) getZ()))
                        .getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        ((EntityLiving) entity).attemptTeleport(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        ((EntityLiving) this.entity).attemptTeleport(entity.getX(), entity.getY(), entity.getZ());
    }
}
