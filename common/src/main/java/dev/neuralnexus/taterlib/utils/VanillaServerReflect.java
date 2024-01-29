package dev.neuralnexus.taterlib.utils;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.server.SimpleServer;

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

    private static final ImmutableMap<ServerType, String> VANILLA_SERVER_CLASSES =
            ImmutableMap.of(
                    ServerType.BUKKIT,
                    VANILLA_SERVER_CLASS_BUKKIT,
                    ServerType.FABRIC,
                    VANILLA_SERVER_CLASS_FABRIC,
                    ServerType.FORGE,
                    VANILLA_SERVER_CLASS_FORGE,
                    ServerType.SPONGE_FORGE,
                    VANILLA_SERVER_CLASS_FORGE);

    /**
     * Get the relocated instance of VanillaServer.
     *
     * @return The instance
     */
    public static SimpleServer getInstance() {
        try {
            return (SimpleServer)
                    Class.forName(
                                    VANILLA_SERVER_CLASSES.getOrDefault(
                                            TaterAPIProvider.serverType(), VANILLA_SERVER_CLASS))
                            .getMethod("getInstance")
                            .invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
