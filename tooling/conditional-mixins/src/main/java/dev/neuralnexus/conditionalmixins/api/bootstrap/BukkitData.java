package dev.neuralnexus.conditionalmixins.api.bootstrap;

import org.bukkit.Bukkit;

public class BukkitData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        return Bukkit.getServer().getVersion();
    }
}
