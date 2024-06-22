package dev.neuralnexus.taterlib.loader.api.data;

import static dev.neuralnexus.taterlib.loader.utils.PathUtils.getPluginsFolder;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import org.bukkit.Bukkit;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Bukkit platform */
public class BukkitData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(Bukkit.getServer().getVersion());
    }

    @Override
    public String modLoaderVersion() {
        return Bukkit.getServer().getVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return Arrays.stream(Bukkit.getServer().getPluginManager().getPlugins())
                .map(
                        plugin ->
                                new ModInfo(
                                        plugin.getName(),
                                        plugin.getName(),
                                        plugin.getDescription().getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    public Path modFolder() {
        return getPluginsFolder();
    }

    @Override
    public Path configFolder() {
        return getPluginsFolder();
    }
}
