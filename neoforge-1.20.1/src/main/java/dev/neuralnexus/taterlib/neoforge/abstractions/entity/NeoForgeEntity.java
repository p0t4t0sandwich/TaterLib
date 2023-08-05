package dev.neuralnexus.taterlib.neoforge.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class NeoForgeEntity implements AbstractEntity {
    private final Entity entity;

    public NeoForgeEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUUID();
    }

    @Override
    public int getEntityId() {
        return entity.getId();
    }

    @Override
    public void remove() {
        entity.remove(Entity.RemovalReason.KILLED);
    }

    @Override
    public String getType() {
        return entity.getType().toString();
    }

    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    @Override
    public void setCustomName(String s) {
        entity.setCustomName(Component.nullToEmpty(s));
    }
}
