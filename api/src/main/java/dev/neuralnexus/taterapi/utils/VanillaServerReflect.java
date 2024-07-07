/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterapi.utils;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.server.SimpleServer;

/** Reflection utilities to get a relocated instance of VanillaServer. */
public class VanillaServerReflect {
    private static final String VANILLA_SERVER_CLASS =
            "dev.neuralnexus.taterlib.vanilla.server.VanillaServer";
    private static final String VANILLA_SERVER_CLASS_BUKKIT =
            "dev.neuralnexus.taterlib.vanilla.bukkit.server.VanillaServer";
    private static final String VANILLA_SERVER_CLASS_FABRIC =
            "dev.neuralnexus.taterlib.vanilla.fabric.server.VanillaServer";
    private static final String VANILLA_SERVER_CLASS_FORGE =
            "dev.neuralnexus.taterlib.vanilla.forge.server.VanillaServer";

    private static final ImmutableMap<Platform, String> VANILLA_SERVER_CLASSES =
            ImmutableMap.of(
                    Platform.BUKKIT,
                    VANILLA_SERVER_CLASS_BUKKIT,
                    Platform.FABRIC,
                    VANILLA_SERVER_CLASS_FABRIC,
                    Platform.FORGE,
                    VANILLA_SERVER_CLASS_FORGE,
                    Platform.SPONGE_FORGE,
                    VANILLA_SERVER_CLASS_FORGE);

    /**
     * Get the relocated instance of VanillaServer.
     *
     * @return The instance
     */
    public static SimpleServer instance() {
        try {
            return (SimpleServer)
                    Class.forName(
                                    VANILLA_SERVER_CLASSES.getOrDefault(
                                            TaterAPIProvider.platform(), VANILLA_SERVER_CLASS))
                            .getMethod("getInstance")
                            .invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
