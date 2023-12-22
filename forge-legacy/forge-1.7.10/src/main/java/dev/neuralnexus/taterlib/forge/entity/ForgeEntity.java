package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

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
        return entity.getCommandSenderName().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getFormattedCommandSenderName() == null) return null;
        return entity.getFormattedCommandSenderName().getFormattedText();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        // TODO: IMPLEMENT NAME TAGS SUPPORT
        //        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new ForgeLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.posX;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.posY;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.posZ;
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return entity.rotationPitch;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.rotationYaw;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.worldObj.provider.getDimensionName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.worldObj
                .provider
                .getBiomeGenForCoords((int) entity.posX, (int) entity.posZ)
                .toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.setPosition(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.setPosition(entity.getX(), entity.getY(), entity.getZ());
    }
}
