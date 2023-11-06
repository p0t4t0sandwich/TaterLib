package dev.neuralnexus.taterlib.common.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;

/**
 * TaterLib API wrapper class
 */
public class TaterAPI {
    private final Data data;

    public TaterAPI(Data data) {
        this.data = data;
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
    public String minecraftVersion() {
        return data.minecraftVersion;
    }

    /**
     * Get server type
     * @return The server type
     */
    public String serverType() {
        return data.serverType;
    }

    /**
     * Add a hook to the hooks map
     * @param hookName The name of the hook
     * @param hook The hook to add
     */
    public void addHook(String hookName, Object hook) {
        data.hooks.put(hookName, hook);
    }

    /**
     * Get if a hook exists
     * @param hookName The name of the hook
     */
    public boolean isHooked(String hookName) {
        return data.hooks.containsKey(hookName);
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
     * Data used throughout the plugin.
     */
    public static class Data {
        public final HashMap<String, Object> hooks = new HashMap<>();
        public Consumer<Set<String>> registerChannels = (channels) -> {};
        public final String configFolder;
        public final String minecraftVersion;
        public final String serverType;

        public Data(String configFolder, String minecraftVersion, String serverType) {
            this.configFolder = configFolder;
            this.minecraftVersion = minecraftVersion;
            this.serverType = serverType;
        }
    }
}
