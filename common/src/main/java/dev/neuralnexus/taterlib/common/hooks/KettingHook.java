package dev.neuralnexus.taterlib.common.hooks;

import org.kettingpowered.ketting.core.Ketting;
import org.kettingpowered.ketting.types.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A hook for Ketting
 * @see <a href="https://github.com/kettingpowered/">Ketting</a>
 */
public class KettingHook {
    private static KettingHook instance;

    /**
     * Create a new hook
     */
    public KettingHook() {
        instance = this;
    }

    /**
     * Get the instance
     * @return The instance
     */
    public static KettingHook get() {
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
        List<Mod> mods = Ketting.getInstance().getAdapter().getMods();
        if (mods == null) {
            return false;
        }
        return mods.stream().anyMatch(mod -> mod.modId().equals(modid));
    }

    /**
     * Get mod list
     * @return The mod list
     */
    public Set<String> getModList() {
        List<Mod> mods = Ketting.getInstance().getAdapter().getMods();
        if (mods == null) {
            return new HashSet<>();
        }
        return mods.stream().map(Mod::modId).collect(Collectors.toSet());
    }

    /**
     * Get KettingCore version
     * @return The KettingCore version
     */
    public String getCoreVersion() {
        return Ketting.getCoreVersion();
    }

    /**
     * Get MC version
     * @return The MC version
     */
    public String getMcVersion() {
        return Ketting.getMcVersion();
    }

    // Maybe public @NotNull DimensionRegistry getDimensionRegistry()
}
