package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.UUID;

/**
 * Abstracts a Fabric entity to an AbstractEntity.
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
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove() {
        entity.remove();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TranslatableComponent(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.getPos().getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.getPos().getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.getPos().getZ();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDimension() {
        return entity.getEntityWorld().getDimension().getType().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        return entity.getEntityWorld().getBiome(entity.getBlockPos()).toString();
    }
}
