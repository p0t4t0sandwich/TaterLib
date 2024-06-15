package org.spongepowered.plugin;

import org.spongepowered.plugin.metadata.PluginMetadata;

import java.util.Optional;

/** Fake Sponge PluginContainer class to simplify the creation of entrypoints. */
public interface PluginContainer {
    PluginMetadata metadata();

    String getName();

    String getId();

    Optional<String> getVersion();
}
