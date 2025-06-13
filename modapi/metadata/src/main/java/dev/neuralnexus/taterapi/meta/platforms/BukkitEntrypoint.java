package dev.neuralnexus.taterapi.meta.platforms;

import dev.neuralnexus.taterapi.meta.Platforms;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitEntrypoint extends JavaPlugin {
    public BukkitEntrypoint() {
        TaterMetadata.init(Platforms.BUKKIT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
