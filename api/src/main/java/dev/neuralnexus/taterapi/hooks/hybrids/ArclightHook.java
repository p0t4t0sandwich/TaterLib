/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.hooks.hybrids;

import dev.neuralnexus.taterapi.hooks.Hook;

import io.izzel.arclight.api.Arclight;
import io.izzel.arclight.api.ArclightVersion;
import io.izzel.arclight.api.TickingTracker;

/**
 * A hook for Arclight
 *
 * @see <a href="https://github.com/IzzelAliz/Arclight">Arclight</a>
 */
public class ArclightHook implements Hook {
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

    @Override
    public String name() {
        return "arclight";
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
    public ArclightVersion version() {
        return Arclight.getVersion();
    }
}
