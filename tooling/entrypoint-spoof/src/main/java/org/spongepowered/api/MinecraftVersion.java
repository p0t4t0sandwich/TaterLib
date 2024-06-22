package org.spongepowered.api;

/** Fake Sponge interface */
public interface MinecraftVersion extends Comparable<MinecraftVersion> {
    String name();

    String getName();

    int protocolVersion();

    boolean isLegacy();
}
