package dev.neuralnexus.taterlib.fabric.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

import java.util.UUID;

public class FabricEntity implements AbstractEntity {
    private final Entity entity;

    public FabricEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
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
        return entity.getCustomName().toString();
    }

    @Override
    public void setCustomName(String s) {
        entity.setCustomName(Text.of(s));
    }
}
