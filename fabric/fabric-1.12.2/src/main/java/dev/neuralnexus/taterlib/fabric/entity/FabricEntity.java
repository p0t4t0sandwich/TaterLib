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
        return entity.getEntityName().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.getPos().x;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getPos().y;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getPos().z;
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
        return entity.world.dimension.getDimensionType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.world.getBiome(entity.getBlockPos()).toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.refreshPositionAfterTeleport(location.getX(), location.getY(), location.getZ());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ());
    }
}
