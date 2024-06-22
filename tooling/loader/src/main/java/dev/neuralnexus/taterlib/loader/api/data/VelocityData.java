package dev.neuralnexus.taterlib.loader.api.data;

import static dev.neuralnexus.taterlib.loader.utils.PathUtils.getPluginsFolder;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Velocity platform. */
public class VelocityData implements PlatformData {
    private static ProxyServer proxyServer;

    public static void setProxyServer(ProxyServer proxyServer) {
        VelocityData.proxyServer = proxyServer;
    }

    @Override
    public MinecraftVersion minecraftVersion() {
        // Reflect to get ProtocolVersion.MAXIMUM_VERSION.toString()
        String version = "Unknown";
        try {
            Class<ProtocolVersion> protocolVersionClass = ProtocolVersion.class;
            Object maximumVersion =
                    protocolVersionClass.getField("MAXIMUM_VERSION").get(protocolVersionClass);
            Object maximumVersionString =
                    maximumVersion.getClass().getMethod("toString").invoke(maximumVersion);
            version = (String) maximumVersionString;
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.from(version);
    }

    @Override
    public String modLoaderVersion() {
        return proxyServer.getVersion().getVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return proxyServer.getPluginManager().getPlugins().stream()
                .map(
                        plugin ->
                                new ModInfo(
                                        plugin.getDescription().getId(),
                                        plugin.getDescription()
                                                .getName()
                                                .orElse("Unknown"),
                                        plugin.getDescription()
                                                .getVersion()
                                                .orElse("Unknown")))
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
