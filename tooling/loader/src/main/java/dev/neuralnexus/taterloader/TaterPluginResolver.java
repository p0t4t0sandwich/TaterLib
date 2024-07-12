/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterloader;

import static dev.neuralnexus.taterloader.TaterReflectUtil.newInstance;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterloader.plugin.Plugin;

/** Locates and returns the core TaterLib plugin. */
public class TaterPluginResolver {
    public static Plugin bukkit() {
        return newInstance("BukkitTaterLibPlugin", Platform.BUKKIT);
    }

    public static Plugin bungeeCord() {
        return newInstance("BungeeTaterLibPlugin", Platform.BUNGEECORD);
    }

    public static Plugin fabric() {
        return newInstance("FabricTaterLibPlugin", Platform.FABRIC);
    }

    public static Plugin forge() {
        return newInstance("ForgeTaterLibPlugin", Platform.FORGE);
    }

    public static Plugin neoForge() {
        return newInstance("NeoForgeTaterLibPlugin", Platform.NEOFORGE);
    }

    public static Plugin sponge() {
        return newInstance("SpongeTaterLibPlugin", Platform.SPONGE);
    }

    public static Plugin velocity() {
        return newInstance("VelocityTaterLibPlugin", Platform.VELOCITY);
    }
}
