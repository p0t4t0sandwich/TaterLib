package dev.neuralnexus.taterlib.loader.api.data;

import static dev.neuralnexus.taterlib.loader.utils.PathUtils.getPluginsFolder;

import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import net.md_5.bungee.api.ProxyServer;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the BungeeCord platform */
public class BungeeCordData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(ProxyServer.getInstance().getVersion());
    }

    @Override
    public String modLoaderVersion() {
        return ProxyServer.getInstance().getVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return ProxyServer.getInstance().getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfo(
                                        plugin.getDescription().getName(),
                                        plugin.getDescription().getName(),
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
