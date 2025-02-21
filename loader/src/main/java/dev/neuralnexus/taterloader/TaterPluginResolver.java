/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterloader;

import static dev.neuralnexus.taterloader.TaterReflectUtil.newInstance;

import dev.neuralnexus.taterapi.loader.plugin.Plugin;
import dev.neuralnexus.taterapi.meta.Platforms;

/** Locates and returns the core TaterLib plugin. */
public class TaterPluginResolver {
    public static Plugin bukkit() {
        return newInstance("BukkitTaterLibPlugin", Platforms.BUKKIT);
    }

    public static Plugin bungeeCord() {
        return newInstance("BungeeTaterLibPlugin", Platforms.BUNGEECORD);
    }

    public static Plugin fabric() {
        return newInstance("FabricTaterLibPlugin", Platforms.FABRIC);
    }

    public static Plugin forge() {
        return newInstance("ForgeTaterLibPlugin", Platforms.FORGE);
    }

    public static Plugin neoForge() {
        return newInstance("NeoForgeTaterLibPlugin", Platforms.NEOFORGE);
    }

    public static Plugin sponge() {
        return newInstance("SpongeTaterLibPlugin", Platforms.SPONGE);
    }

    public static Plugin velocity() {
        return newInstance("VelocityTaterLibPlugin", Platforms.VELOCITY);
    }
}
