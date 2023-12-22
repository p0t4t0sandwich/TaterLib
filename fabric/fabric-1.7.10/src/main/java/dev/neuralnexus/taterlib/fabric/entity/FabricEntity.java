package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

import java.util.UUID;

/** Fabric implementation of {@link Entity}. */
public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Fabric entity.
     *
     * @return The Fabric entity.
     */
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
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
        return entity.getTranslationKey().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getName() == null) return null;
        return entity.getName().asFormattedString();
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
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.x;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.y;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.z;
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return (float) entity.getRotation().x;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return (float) entity.getRotation().y;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.world.dimension.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.world.getBiome((int) entity.x, (int) entity.z).toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.updatePosition(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.updatePosition(entity.getX(), entity.getY(), entity.getZ());
    }
}
