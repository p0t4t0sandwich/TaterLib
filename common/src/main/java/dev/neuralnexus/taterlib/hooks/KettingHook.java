package dev.neuralnexus.taterlib.hooks;

import dev.neuralnexus.taterlib.api.TaterAPI;

import org.kettingpowered.ketting.adapter.BukkitAdapter;
import org.kettingpowered.ketting.adapter.ForgeAdapter;
import org.kettingpowered.ketting.core.Ketting;
import org.kettingpowered.ketting.types.Mod;
import org.kettingpowered.ketting.types.Plugin;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A hook for Ketting
 *
 * @see <a href="https://github.com/kettingpowered/">Ketting</a>
 */
public class KettingHook {
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

    /**
     * Get mod list
     *
     * @return The mod list
     */
    public Set<String> getModList() {
        Optional<ForgeAdapter> adapter = Ketting.getInstance().getForgeAdapter();
        if (!adapter.isPresent()) {
            return new HashSet<>();
        }
        List<Mod> mods = adapter.get().getMods();
        if (mods == null) {
            return new HashSet<>();
        }
        return mods.stream().map(Mod::modId).collect(Collectors.toSet());
    }

    /**
     * Get plugin list
     *
     * @return The plugin list
     */
    public Set<String> getPluginList() {
        Optional<BukkitAdapter> adapter = Ketting.getInstance().getBukkitAdapter();
        if (!adapter.isPresent()) {
            return new HashSet<>();
        }
        List<Plugin> plugins = adapter.get().getPlugins();
        if (plugins == null) {
            return new HashSet<>();
        }
        return plugins.stream().map(plugin -> plugin.info().name()).collect(Collectors.toSet());
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
    public String getCoreVersion() {
        return Ketting.getCoreVersion();
    }

    /**
     * Get MC version
     *
     * @return The MC version
     */
    public String getMcVersion() {
        return Ketting.getMcVersion();
    }

    // Maybe public @NotNull DimensionRegistry getDimensionRegistry()
}
