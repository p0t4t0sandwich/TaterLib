package dev.neuralnexus.taterlib.common.api;

import dev.neuralnexus.taterlib.common.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.server.Server;

import java.util.function.Predicate;
import java.util.function.Supplier;

/** API wrapper class */
public class TaterAPI {
    private final Data data;

    public TaterAPI(String configFolder, MinecraftVersion minecraftVersion, ServerType serverType) {
        this.data = new Data(configFolder, minecraftVersion, serverType);
    }

    /**
     * Get config folder
     *
     * @return The config folder
     */
    public String configFolder() {
        return data.configFolder;
    }

    /**
     * Get Minecraft version
     *
     * @return The Minecraft version
     */
    public MinecraftVersion minecraftVersion() {
        return data.minecraftVersion;
    }

    /**
     * Get server type
     *
     * @return The server type
     */
    public ServerType serverType() {
        return data.serverType;
    }

    /**
     * Weather Brigadier is supported
     *
     * @return If Brigadier is supported
     */
    public boolean isBrigadierSupported() {
        return (data.minecraftVersion.isAtLeast(MinecraftVersion.V1_13)
                        && data.serverType.is(
                                ServerType.FORGE,
                                ServerType.NEOFORGE,
                                ServerType.GOLDENFORGE,
                                ServerType.FABRIC,
                                ServerType.QUILT))
                || data.serverType.isVelocityBased();
    }

    /**
     * Set the isPluginLoaded predicate
     *
     * @param isPluginLoaded The isPluginLoaded predicate
     */
    public void setIsPluginLoaded(Predicate<String> isPluginLoaded) {
        data.isPluginLoaded = isPluginLoaded;
    }

    /**
     * Set the isModLoaded predicate
     *
     * @param isModLoaded The isModLoaded predicate
     */
    public void setIsModLoaded(Predicate<String> isModLoaded) {
        data.isModLoaded = isModLoaded;
    }

    /**
     * Get if a plugin is loaded
     *
     * @param pluginName The name of the plugin
     * @return If the plugin is loaded
     */
    public boolean isPluginLoaded(String pluginName) {
        return data.isPluginLoaded.test(pluginName);
    }

    /**
     * Get if a mod is loaded
     *
     * @param modid The modid of the mod
     * @return If the mod is loaded
     */
    public boolean isModLoaded(String modid) {
        return data.isModLoaded.test(modid);
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link dev.neuralnexus.taterlib.common.event.api.ServerEvents#STARTED}
     * <br>
     * 2nd Note: When looking for a plugin, the name is case-sensitive. <br>
     * For example, "luckperms" will not match "LuckPerms". <br>
     * When considering cross-API libraries, it's best to use the capitalized name, as this method
     * runs the check again to check for a modid. <br>
     * For example, "LuckPerms" will match "luckperms" and "LuckPerms".
     *
     * @param pluginNameOrModId The name of the plugin or modid of the mod
     */
    public boolean isPluginModLoaded(String pluginNameOrModId) {
        return data.isPluginLoaded.test(pluginNameOrModId)
                || data.isModLoaded.test(pluginNameOrModId.toLowerCase());
    }

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public Server getServer() {
        return data.minecraftServer.get();
    }

    /**
     * Set the minecraftServer supplier
     *
     * @param minecraftServer The minecraftServer supplier
     */
    public void setServer(Supplier<Server> minecraftServer) {
        data.minecraftServer = minecraftServer;
    }

    /** Data used throughout the plugin via the API. */
    static class Data {
        final String configFolder;
        final MinecraftVersion minecraftVersion;
        final ServerType serverType;
        Predicate<String> isModLoaded = (modid) -> false;
        Predicate<String> isPluginLoaded = (pluginName) -> false;
        Supplier<Server> minecraftServer = () -> null;

        Data(String configFolder, MinecraftVersion minecraftVersion, ServerType serverType) {
            this.configFolder = configFolder;
            this.minecraftVersion = minecraftVersion;
            this.serverType = serverType;
        }
    }
}
