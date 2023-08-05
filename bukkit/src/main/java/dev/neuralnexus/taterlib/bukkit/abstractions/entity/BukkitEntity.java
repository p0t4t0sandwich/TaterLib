package dev.neuralnexus.taterlib.bukkit.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class BukkitEntity implements AbstractEntity {
    private final Entity entity;

    public BukkitEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public String getType() {
        return entity.getType().toString();
    }

    @Override
    public String getCustomName() {
        return entity.getCustomName();
    }

    @Override
    public void setCustomName(String s) {
        entity.setCustomName(s);
    }
}
