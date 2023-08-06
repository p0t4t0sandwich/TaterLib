package dev.neuralnexus.taterlib.neoforge.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

/**
 * Abstracts a NeoForge entity to an AbstractEntity.
 */
public class NeoForgeEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The NeoForge entity.
     */
    public NeoForgeEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUUID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getEntityId() {
        return entity.getId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove() {
        entity.remove(Entity.RemovalReason.KILLED);
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
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(Component.nullToEmpty(name));
    }
}