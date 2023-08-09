package dev.neuralnexus.taterlib.forge.abstrations.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

/**
 * Abstracts a Forge entity to an AbstractEntity.
 */
public class ForgeEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The Forge entity.
     */
    public ForgeEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueID();
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
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new StringTextComponent(name));
    }
}
