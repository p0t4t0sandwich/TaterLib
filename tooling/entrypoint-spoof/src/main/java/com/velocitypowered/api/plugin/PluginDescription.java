package com.velocitypowered.api.plugin;

import java.util.Optional;

/** Fake Velocity plugin description interface. */
public interface PluginDescription {
    String getId();

    default Optional<String> getName() {
        return Optional.empty();
    }

    default Optional<String> getVersion() {
        return Optional.empty();
    }

    default Optional<String> getDescription() {
        return Optional.empty();
    }
}
