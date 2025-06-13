package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.Platforms;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeCordEntrypoint extends Plugin {
    public BungeeCordEntrypoint() {
        TaterMetadata.init(Platforms.BUNGEECORD);
    }
}
