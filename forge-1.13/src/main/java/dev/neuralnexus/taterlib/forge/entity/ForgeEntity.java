package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

/**
 * Forge implementation of {@link Entity}.
 */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        entity.remove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return entity.getType().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TextComponentString(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocation() {
        return new ForgeLocation(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return entity.getPosition().getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return entity.getPosition().getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return entity.getPosition().getZ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYaw() {
        return entity.getPitchYaw().x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPitch() {
        return entity.getPitchYaw().y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDimension() {
        ResourceLocation resourceLocation = entity.world.dimension.getType().getRegistryName();
        if (resourceLocation == null) return null;
        return resourceLocation.getNamespace() + ":" + resourceLocation.getPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry = entity.world.getBiome(
                entity.world.getChunk(entity.getPosition()).getPos().asBlockPos()
        ).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleport(Location location) {
        ((EntityLiving) entity).attemptTeleport(location.getX(), location.getY(), location.getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleport(Entity entity) {
        ((EntityLiving) this.entity).attemptTeleport(entity.getX(), entity.getY(), entity.getZ());
    }
}
