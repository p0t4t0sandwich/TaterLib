package org.spongepowered.api.service.permission;

/**
 * Fake Sponge Subject class
 */
public interface Subject {
    boolean hasPermission(String permission);
}
