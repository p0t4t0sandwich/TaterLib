package dev.neuralnexus.taterapi.meta.platforms;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeCordEntrypoint extends Plugin {
    public BungeeCordEntrypoint() {
        TaterMetadata.initBungeeCord();
    }
}
