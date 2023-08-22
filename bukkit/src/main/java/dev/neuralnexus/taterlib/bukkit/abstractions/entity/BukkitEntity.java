package dev.neuralnexus.taterlib.bukkit.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import org.bukkit.entity.Entity;

import java.util.UUID;

/**
 * Abstracts a Bukkit entity to an AbstractEntity.
 */
public class BukkitEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The Bukkit entity.
     */
    public BukkitEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
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
        return entity.getType().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        return entity.getCustomName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.getLocation().getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.getLocation().getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.getLocation().getZ();
    }
}
