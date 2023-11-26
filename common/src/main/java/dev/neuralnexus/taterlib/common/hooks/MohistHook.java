package dev.neuralnexus.taterlib.common.hooks;

import com.mohistmc.api.ServerAPI;

import java.util.Set;

/**
 * A hook for Mohist
 * @see <a href="https://github.com/MohistMC/Mohist">Mohist</a>
 */
public class MohistHook {
    private static MohistHook instance;

    /**
     * Create a new hook
     */
    public MohistHook() {
        instance = this;
    }

    /**
     * Get the instance
     * @return The instance
     */
    public static MohistHook get() {
        return instance;
    }

    /**
     * Get if a mod is loaded
     * <br>
     * <b>NOTE: You can just use {@link dev.neuralnexus.taterlib.common.api.TaterAPI#isPluginLoaded(String)}</b>
     * @param modid The modid
     * @return If the mod is loaded
     */
    public boolean hasMod(String modid) {
        return ServerAPI.hasMod(modid);
    }

    /**
     * Get if a plugin is loaded
     * <br>
     * <b>NOTE: You can just use {@link dev.neuralnexus.taterlib.common.api.TaterAPI#isPluginLoaded(String)}</b>
     * @param pluginName The plugin name
     * @return If the plugin is loaded
     */
    public boolean hasPlugin(String pluginName) {
        return ServerAPI.hasPlugin(pluginName);
    }

    /**
     * Get mod list
     * @return The mod list
     */
    public Set<String> getModList() {
        return ServerAPI.modlists;
    }

    // Maybe public static MinecraftServer getNMSServer()
}
