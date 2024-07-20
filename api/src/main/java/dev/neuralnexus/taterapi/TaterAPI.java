/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.metadata.PlatformData;
import dev.neuralnexus.taterapi.server.SimpleServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/** API wrapper class */
public class TaterAPI {
    private final PlatformData[] platformData;

    private Supplier<SimpleServer> minecraftServer = () -> null;

    public TaterAPI(PlatformData... platformData) {
        if (platformData.length > 0) {
            this.platformData = platformData;
        } else {
            this.platformData = new PlatformData[] {PlatformData.instance()};
        }
    }

    /**
     * Get the platform data
     *
     * @return The platform data
     */
    public PlatformData platformData() {
        return platformData[0];
    }

    /**
     * Get the Minecraft version
     *
     * @return The Minecraft version
     */
    public MinecraftVersion minecraftVersion() {
        return platformData[0].minecraftVersion();
    }

    /**
     * Get the mod loader version
     *
     * @return The mod loader version
     */
    public String modLoaderVersion() {
        return platformData[0].modLoaderVersion();
    }

    /**
     * Get the mod list
     *
     * @return The mod list
     */
    public List<ModInfo> modList() {
        List<ModInfo> mods = new ArrayList<>();
        for (PlatformData data : platformData) {
            mods.addAll(data.modList());
        }
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
        for (PlatformData data : platformData) {
            if (data.isModLoaded(nameOrId)) {
                return true;
            }
        }
        return false;
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
