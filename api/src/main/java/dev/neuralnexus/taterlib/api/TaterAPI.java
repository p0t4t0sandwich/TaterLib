package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.impl.metadata.PlatformDataImpl;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.server.SimpleServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/** API wrapper class */
public class TaterAPI {
    private final Set<PlatformData> platformData;

    private Supplier<SimpleServer> minecraftServer = () -> null;

    public TaterAPI(PlatformData... platformData) {
        if (platformData.length > 0) {
            this.platformData = Arrays.stream(platformData).collect(Collectors.toSet());
        } else {
            Set<PlatformData> platform = new HashSet<>();
            platform.add(new PlatformDataImpl());
            this.platformData = platform;
        }
    }

    /**
     * Get the platform data
     *
     * @return The platform data
     */
    public PlatformData platformData() {
        return platformData.stream().findFirst().get();
    }

    /**
     * Get the Minecraft version
     *
     * @return The Minecraft version
     */
    public MinecraftVersion minecraftVersion() {
        return platformData.stream().findFirst().get().minecraftVersion();
    }

    /**
     * Get the mod loader version
     *
     * @return The mod loader version
     */
    public String modLoaderVersion() {
        return platformData.stream().findFirst().get().modLoaderVersion();
    }

    /**
     * Get the mod list
     *
     * @return The mod list
     */
    public List<ModInfo> modList() {
        List<ModInfo> mods = new ArrayList<>();
        platformData.forEach(data -> mods.addAll(data.modList()));
        return mods;
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED}
     *
     * @param nameOrId The name of the plugin or modId of the mod
     */
    public boolean isModLoaded(String nameOrId) {
        return platformData.stream().anyMatch(data -> data.isModLoaded(nameOrId));
    }

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public SimpleServer server() {
        return minecraftServer.get();
    }

    /**
     * Set the minecraftServer supplier
     *
     * @param minecraftServer The minecraftServer supplier
     */
    @ApiStatus.Internal
    public void setServer(Supplier<SimpleServer> minecraftServer) {
        this.minecraftServer = minecraftServer;
    }
}
