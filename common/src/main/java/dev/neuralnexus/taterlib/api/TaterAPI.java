package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.server.Server;

import java.util.function.Predicate;
import java.util.function.Supplier;

/** API wrapper class */
public class TaterAPI {
    private final String configFolder;
    private Predicate<String> isModLoaded = (modid) -> false;
    private Predicate<String> isPluginLoaded = (pluginName) -> false;
    private Supplier<Server> minecraftServer = () -> null;

    public TaterAPI(String configFolder) {
        this.configFolder = configFolder;
    }

    /**
     * Get config folder
     *
     * @return The config folder
     */
    public String configFolder() {
        return configFolder;
    }

    /**
     * Set the isPluginLoaded predicate
     *
     * @param isPluginLoaded The isPluginLoaded predicate
     */
    public void setIsPluginLoaded(Predicate<String> isPluginLoaded) {
        this.isPluginLoaded = isPluginLoaded;
    }

    /**
     * Set the isModLoaded predicate
     *
     * @param isModLoaded The isModLoaded predicate
     */
    public void setIsModLoaded(Predicate<String> isModLoaded) {
        this.isModLoaded = isModLoaded;
    }

    /**
     * Get if a plugin is loaded
     *
     * @param pluginName The name of the plugin
     * @return If the plugin is loaded
     */
    public boolean isPluginLoaded(String pluginName) {
        return isPluginLoaded.test(pluginName);
    }

    /**
     * Get if a mod is loaded
     *
     * @param modid The modid of the mod
     * @return If the mod is loaded
     */
    public boolean isModLoaded(String modid) {
        return isModLoaded.test(modid);
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED} <br>
     * 2nd Note: When looking for a plugin, the name is case-sensitive. <br>
     * For example, "luckperms" will not match "LuckPerms". <br>
     * When considering cross-API libraries, it's best to use the capitalized name, as this method
     * runs the check again to check for a lowercase modid. <br>
     * For example, "LuckPerms" will match "LuckPerms" and "luckperms".
     *
     * @param pluginNameOrModId The name of the plugin or modid of the mod
     */
    public boolean isPluginModLoaded(String pluginNameOrModId) {
        return isPluginLoaded.test(pluginNameOrModId)
                || isModLoaded.test(pluginNameOrModId.toLowerCase());
    }

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public Server getServer() {
        return minecraftServer.get();
    }

    /**
     * Set the minecraftServer supplier
     *
     * @param minecraftServer The minecraftServer supplier
     */
    public void setServer(Supplier<Server> minecraftServer) {
        this.minecraftServer = minecraftServer;
    }
}
