package dev.neuralnexus.taterapi.meta.platforms;

import org.bukkit.plugin.java.JavaPlugin;

public class BukkitEntrypoint extends JavaPlugin {
    public BukkitEntrypoint() {
        TaterMetadata.initBukkit();
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
