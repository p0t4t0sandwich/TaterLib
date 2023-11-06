package dev.neuralnexus.taterlib.common.api;

import dev.neuralnexus.taterlib.common.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.common.api.info.ServerType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * API wrapper class
 */
public class TaterAPI {
    private final Data data;

    public TaterAPI(String configFolder, String minecraftVersion) {
        this.data = new Data(configFolder, minecraftVersion);
    }

    /**
     * Get config folder
     * @return The config folder
     */
    public String configFolder() {
        return data.configFolder;
    }

    /**
     * Get Minecraft version
     * @return The Minecraft version
     */
    public MinecraftVersion minecraftVersion() {
        return data.minecraftVersion;
    }

    /**
     * Get server type
     * @return The server type
     */
    public ServerType serverType() {
        return data.serverType;
    }

    /**
     * Add a hook to the hooks map
     * @param hookName The name of the hook
     * @param hook The hook to add
     */
    public void addHook(String hookName, Object hook) {
        data.hooks.put(hookName.toLowerCase(), hook);
    }

    /**
     * Get if a hook exists
     * @param hookName The name of the hook
     */
    public boolean isHooked(String hookName) {
        return data.hooks.containsKey(hookName.toLowerCase());
    }

    /**
     * Set the registerChannels consumer
     * @param registerChannels The registerChannels consumer
     */
    public void setRegisterChannels(Consumer<Set<String>> registerChannels) {
        data.registerChannels = registerChannels;
    }

    /**
     * Register a channel
     * @param channel The channel to register
     */
    public void registerChannel(String channel) {
        data.registerChannels.accept(Collections.singleton(channel));
    }

    /**
     * Register channels
     * @param channels The channels to register
     */
    public void registerChannels(Set<String> channels) {
        data.registerChannels.accept(channels);
    }

    /**
     * Weather Brigadier is supported
     * @return If Brigadier is supported
     */
    public boolean isBrigadierSupported() {
        if (data.minecraftVersion.isAtLeast(MinecraftVersion.V1_13)) {
            switch (data.serverType) {
                case FORGE:
                case NEOFORGE:
                case GOLDENFORGE:
                case FABRIC:
                case QUILT:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * Set the isPluginLoaded predicate
     * @param isPluginLoaded The isPluginLoaded predicate
     */
    public void setIsPluginLoaded(Predicate<String> isPluginLoaded) {
        data.isPluginLoaded = isPluginLoaded;
    }

    /**
     * Get if a plugin/mod is loaded
     * <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the server has started {@link dev.neuralnexus.taterlib.common.event.api.ServerEvents#STARTED}
     * <br>
     * 2nd Note: When looking for a plugin, the name is case-sensitive.
     * <br>
     * For example, "luckperms" will not match "LuckPerms".
     * <br>
     * When considering cross-API libraries, it's best to use the capitalized name, as this method runs the check again to check for a modid.
     * <br>
     * For example, "LuckPerms" will match "luckperms" and "LuckPerms".
     * @param pluginName The name of the plugin/mod
     */
    public boolean isPluginLoaded(String pluginName) {
        return data.isPluginLoaded.test(pluginName) || data.isPluginLoaded.test(pluginName.toLowerCase());
    }

    /**
     * Data used throughout the plugin via the API.
     */
    static class Data {
        final HashMap<String, Object> hooks = new HashMap<>();
        Consumer<Set<String>> registerChannels = (channels) -> {};
        Predicate<String> isPluginLoaded = (pluginName) -> false;
        final String configFolder;
        final MinecraftVersion minecraftVersion;
        final ServerType serverType;

        Data(String configFolder, String minecraftVersion) {
            this.configFolder = configFolder;
            this.minecraftVersion = MinecraftVersion.from(minecraftVersion);
            this.serverType = ServerType.getServerType();
        }
    }
}
