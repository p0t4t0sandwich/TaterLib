/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.plugin;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;

/** Note: When TaterLib is loaded, all instances of this interface will be initialized */
public interface ResolvableEntrypoint {
    /**
     * Get the mappings the plugin implementation is supposed to run in. Default returns
     * `Mappings.NONE` to indicate no constraint.
     */
    default Mappings mappings() {
        return Mappings.NONE;
    }

    /**
     * The platform this plugin is supposed to run on. Default returns `Platforms.UNKNOWN` to
     * indicate no constraint.
     */
    default Platform platform() {
        return Platforms.UNKNOWN;
    }

    default Platform[] platforms() {
        return new Platform[] {this.platform()};
    }

    // TODO: Consider adding a primaryPlatform() method

    /**
     * The minimum Minecraft version this plugin is supposed to run on. Default returns
     * `MinecraftVersions.Unknown` to indicate no bound.
     */
    default MinecraftVersion min() {
        return MinecraftVersions.UNKNOWN;
    }

    /**
     * The maximum Minecraft version this plugin is supposed to run on. Default returns
     * `MinecraftVersions.Unknown` to indicate no bound.
     */
    default MinecraftVersion max() {
        return MinecraftVersions.UNKNOWN;
    }

    /** Called when the plugin is initialized, equivalent to running code in the constructor */
    void onInit();

    /** Called during the platform's common enable phase */
    default void onEnable() {}
}
