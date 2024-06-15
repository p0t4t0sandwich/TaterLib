package org.spongepowered.plugin.metadata;

import java.util.Optional;

/** Fake Sponge interface */
public interface PluginMetadata extends Inheritable {
    String id();
    Optional<String> name();
}
