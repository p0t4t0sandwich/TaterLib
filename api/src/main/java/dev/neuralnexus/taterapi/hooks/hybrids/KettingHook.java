/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.hooks.hybrids;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.hooks.Hook;

import org.kettingpowered.ketting.adapter.BukkitAdapter;
import org.kettingpowered.ketting.adapter.ForgeAdapter;
import org.kettingpowered.ketting.core.Ketting;
import org.kettingpowered.ketting.types.Mod;
import org.kettingpowered.ketting.types.Plugin;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A hook for Ketting
 *
 * @see <a href="https://github.com/kettingpowered/">Ketting</a>
 */
public class KettingHook implements Hook {
    private static KettingHook instance;

    /** Create a new hook */
    public KettingHook() {
        instance = this;
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static KettingHook get() {
        return instance;
    }

    @Override
    public String name() {
        return "ketting";
    }

    /**
     * Get mod list
     *
     * @return The mod list
     */
    public List<String> modList() {
        Optional<ForgeAdapter> adapter = Ketting.getInstance().getForgeAdapter();
        if (!adapter.isPresent()) {
            return Collections.emptyList();
        }
        List<Mod> mods = adapter.get().getMods();
        if (mods == null) {
            return Collections.emptyList();
        }
        return mods.stream().map(Mod::modId).collect(Collectors.toList());
    }

    /**
     * Get plugin list
     *
     * @return The plugin list
     */
    public List<String> pluginList() {
        Optional<BukkitAdapter> adapter = Ketting.getInstance().getBukkitAdapter();
        if (!adapter.isPresent()) {
            return Collections.emptyList();
        }
        List<Plugin> plugins = adapter.get().getPlugins();
        if (plugins == null) {
            return Collections.emptyList();
        }
        return plugins.stream().map(plugin -> plugin.info().name()).collect(Collectors.toList());
    }

    /**
     * Get if a mod is loaded <br>
     * <b>NOTE: You can just use {@link TaterAPI#isModLoaded(String)}</b>
     *
     * @param modid The modid
     * @return If the mod is loaded
     */
    public boolean hasMod(String modid) {
        return Ketting.getInstance()
                .getForgeAdapter()
                .filter(forgeAdapter -> forgeAdapter.getMod(modid) != null)
                .isPresent();
    }

    /**
     * Get if a plugin is loaded <br>
     * <b>NOTE: You can just use {@link TaterAPI#isPluginLoaded(String)}</b>
     *
     * @param pluginName The plugin name
     * @return If the plugin is loaded
     */
    public boolean hasPlugin(String pluginName) {
        return Ketting.getInstance()
                .getBukkitAdapter()
                .filter(bukkitAdapter -> bukkitAdapter.getPlugin(pluginName) != null)
                .isPresent();
    }

    /**
     * Get KettingCore version
     *
     * @return The KettingCore version
     */
    public String coreVersion() {
        return Ketting.getCoreVersion();
    }

    /**
     * Get MC version
     *
     * @return The MC version
     */
    public String mcVersion() {
        return Ketting.getMcVersion();
    }

    // Maybe public @NotNull DimensionRegistry getDimensionRegistry()
}
