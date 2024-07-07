/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.hooks.hybrids;

import com.mohistmc.api.ServerAPI;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.hooks.Hook;

import java.util.Set;

/**
 * A hook for Mohist
 *
 * @see <a href="https://github.com/MohistMC/Mohist">Mohist</a>
 */
public class MohistHook implements Hook {
    private static MohistHook instance;

    /** Create a new hook */
    public MohistHook() {
        instance = this;
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static MohistHook get() {
        return instance;
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return "mohist";
    }

    /**
     * Get if a mod is loaded <br>
     * <b>NOTE: You can just use {@link TaterAPI#isModLoaded(String)}</b>
     *
     * @param modid The modid
     * @return If the mod is loaded
     */
    public boolean hasMod(String modid) {
        return ServerAPI.hasMod(modid);
    }

    /**
     * Get if a plugin is loaded <br>
     * <b>NOTE: You can just use {@link TaterAPI#isModLoaded(String)}</b>
     *
     * @param pluginName The plugin name
     * @return If the plugin is loaded
     */
    public boolean hasPlugin(String pluginName) {
        return ServerAPI.hasPlugin(pluginName);
    }

    /**
     * Get mod list
     *
     * @return The mod list
     */
    public Set<String> modList() {
        return ServerAPI.modlists;
    }

    // Maybe public static MinecraftServer getNMSServer()
}
