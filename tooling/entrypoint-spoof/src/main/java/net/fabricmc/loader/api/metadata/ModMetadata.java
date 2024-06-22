package net.fabricmc.loader.api.metadata;

import net.fabricmc.loader.api.Version;

/** Fake Fabric interface. */
public interface ModMetadata {
    String getId();

    String getName();

    Version getVersion();
}
