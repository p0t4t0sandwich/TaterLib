package dev.neuralnexus.taterlib.common.abstractions.entity;

import java.util.UUID;

public interface AbstractEntity {
    UUID getUniqueId();
    int getEntityId();
    void remove();
    String getType();
    String getCustomName();
    void setCustomName(String name);
}
