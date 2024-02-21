package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.server.SimpleServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

/** API wrapper class */
public class TaterAPI {
    private Supplier<Set<PluginInfo>> pluginList = Collections::emptySet;
    private Supplier<Set<ModInfo>> modList = Collections::emptySet;
    private Supplier<SimpleServer> minecraftServer = () -> null;

    /**
     * Get the plugin list
     *
     * @return The plugin list
     */
    public Set<PluginInfo> pluginList() {
        return pluginList.get();
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY <br>
     * Set the pluginList supplier
     *
     * @param pluginList The pluginList supplier
     */
    @ApiStatus.Internal
    public void setPluginList(Supplier<Set<PluginInfo>> pluginList) {
        this.pluginList = pluginList;
    }

    /**
     * Get the mod list
     *
     * @return The mod list
     */
    public Set<ModInfo> modList() {
        return modList.get();
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY <br>
     * Set the modList supplier
     *
     * @param modList The modList supplier
     */
    @ApiStatus.Internal
    public void setModList(Supplier<Set<ModInfo>> modList) {
        this.modList = modList;
    }

    /**
     * Get if a plugin is loaded
     *
     * @param pluginName The name of the plugin
     * @return If the plugin is loaded
     */
    public boolean isPluginLoaded(String pluginName) {
        return pluginList.get().stream()
                .anyMatch(plugin -> plugin.name().equalsIgnoreCase(pluginName));
    }

    /**
     * Get if a mod is loaded
     *
     * @param modId The modId of the mod
     * @return If the mod is loaded
     */
    public boolean isModLoaded(String modId) {
        return modList.get().stream().anyMatch(mod -> mod.id().equalsIgnoreCase(modId));
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED}
     *
     * @param pluginNameOrModId The name of the plugin or modId of the mod
     */
    public boolean isPluginModLoaded(String pluginNameOrModId) {
        return isPluginLoaded(pluginNameOrModId) || isModLoaded(pluginNameOrModId);
    }

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public SimpleServer getServer() {
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
