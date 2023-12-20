package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

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
        ResourceLocation resourceLocation = entity.level.dimension.getType().getRegistryName();
        if (resourceLocation == null) return null;
        return resourceLocation.getNamespace() + ":" + resourceLocation.getPath();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry =
                entity.level.getBiome(entity.getCommandSenderBlockPosition()).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.teleportTo(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.teleportTo(entity.getX(), entity.getY(), entity.getZ());
    }
}
