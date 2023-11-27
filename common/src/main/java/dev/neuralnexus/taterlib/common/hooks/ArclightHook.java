package dev.neuralnexus.taterlib.common.hooks;

import io.izzel.arclight.api.Arclight;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.TickingTracker;

/**
 * A hook for Arclight
 *
 * @see <a href="https://github.com/IzzelAliz/Arclight">Arclight</a>
 */
public class ArclightHook {
    private static ArclightHook instance;

    /** Create a new hook */
    public ArclightHook() {
        instance = this;
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static ArclightHook get() {
        return instance;
    }

    /** Get the {@link TickingTracker} return The {@link TickingTracker} */
    public static TickingTracker getTickingTracker() {
        return Arclight.getTickingTracker();
    }

    /**
     * Register a Forge event from Bukkit Don't really need this atm due to TaterLib's event system,
     * but a maybe for the future Would need to reflect to grab the Forge event bus, or split off
     * Arclight into its own submodule
     */
    //    public static void registerForgeEvent(Plugin plugin, IEventBus eventBus, Object target)

    /**
     * Get the Arclight version
     *
     * @return The Arclight version
     */
    public ArclightVersion getVersion() {
        return Arclight.getVersion();
    }
}
