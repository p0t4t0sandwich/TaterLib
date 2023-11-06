package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import net.minecraft.text.LiteralText;

import java.util.UUID;

/**
 * Fabric implementation of {@link Entity}.
 */
public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Fabric entity.
     * @return The Fabric entity.
     */
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEntityId() {
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        entity.remove(net.minecraft.entity.Entity.RemovalReason.KILLED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new LiteralText(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocation() {
        return new FabricLocation(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return entity.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return entity.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return entity.getZ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYaw() {
        return entity.getYaw(0F);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPitch() {
        return entity.getPitch(0F);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDimension() {
        return entity.getEntityWorld().getRegistryKey().getValue().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBiome() {
        return entity.getEntityWorld().getBiome(entity.getBlockPos()).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleport(Location location) {
        entity.requestTeleport(location.getX(), location.getY(), location.getZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void teleport(Entity entity) {
        this.entity.requestTeleport(entity.getX(), entity.getY(), entity.getZ());
    }
}
