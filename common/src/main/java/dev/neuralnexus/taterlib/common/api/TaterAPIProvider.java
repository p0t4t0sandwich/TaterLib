package dev.neuralnexus.taterlib.common.api;

import dev.neuralnexus.taterlib.common.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.common.api.info.ServerType;
import dev.neuralnexus.taterlib.common.hooks.ArclightHook;
import dev.neuralnexus.taterlib.common.hooks.KettingHook;
import dev.neuralnexus.taterlib.common.hooks.MagmaHook;
import dev.neuralnexus.taterlib.common.hooks.MohistHook;

import java.util.HashMap;

/**
 * API Provider
 */
public class TaterAPIProvider {
    private static final HashMap<ServerType, TaterAPI> apis = new HashMap<>();
    private static final HashMap<String, Object> hooks = new HashMap<>();

    /**
     * Add a hook to the hooks map
     * @param hookName The name of the hook
     * @param hook The hook to add
     */
    public static void addHook(String hookName, Object hook) {
        hooks.put(hookName.toLowerCase(), hook);
    }

    /**
     * Get if a hook exists
     * @param hookName The name of the hook
     */
    public static boolean isHooked(String hookName) {
        return hooks.containsKey(hookName.toLowerCase());
    }

    /**
     * Get the instance of the API
     * @return The instance of the API
     */
    public static TaterAPI get() {
        if (apis.isEmpty()) {
            throw new NotLoadedException(ServerType.getServerType());
        }
        return apis.values().iterator().next();
    }

    /**
     * Get the instance of the API for a specific server type
     * @param serverType The server type
     * @return The instance of the API
     */
    public static TaterAPI get(ServerType serverType) {
        if (apis.containsKey(serverType)) {
            return apis.get(serverType);
        }
        throw new NotLoadedException(serverType);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param minecraftVersion The Minecraft version
     */
    public static void register(MinecraftVersion minecraftVersion) {
        ServerType serverType = ServerType.getServerType();

        if (serverType.isBukkitBased()) {
            apis.put(ServerType.BUKKIT, new TaterAPI("plugins", minecraftVersion, serverType));
        }

        if (serverType.isBungeeCordBased()) {
            apis.put(ServerType.BUNGEECORD, new TaterAPI("plugins", minecraftVersion, serverType));
        }

        if (serverType.isFabricBased()) {
            apis.put(ServerType.FABRIC, new TaterAPI("config", minecraftVersion, serverType));
        }

        if (serverType.is(ServerType.NEOFORGE)) {
            apis.put(ServerType.NEOFORGE, new TaterAPI("config", minecraftVersion, serverType));
        } else if (serverType.isForgeBased()) {
            apis.put(ServerType.FORGE, new TaterAPI("config", minecraftVersion, serverType));
        }

        if (serverType.isSpongeBased()) {
            apis.put(ServerType.SPONGE, new TaterAPI("config", minecraftVersion, serverType));
        }

        if (serverType.isVelocityBased()) {
            apis.put(ServerType.VELOCITY, new TaterAPI("plugins", minecraftVersion, serverType));
        }

        if (serverType.isHybrid()) {
            TaterAPI bukkitApi = apis.get(ServerType.BUKKIT);
            TaterAPI forgeApi = apis.get(ServerType.FORGE);

            switch (serverType) {
                case MOHIST:
                    MohistHook mohistHook = new MohistHook();
                    hooks.put("mohist", mohistHook);
                    bukkitApi.setIsModLoaded(mohistHook::hasMod);
                    bukkitApi.setIsPluginLoaded(mohistHook::hasPlugin);
                    forgeApi.setIsModLoaded(mohistHook::hasMod);
                    forgeApi.setIsPluginLoaded(mohistHook::hasPlugin);
                    break;
                case MAGMA:
                    MagmaHook magmaHook = new MagmaHook();
                    hooks.put("magma", magmaHook);
                    bukkitApi.setIsModLoaded(magmaHook::hasMod);
                    forgeApi.setIsModLoaded(magmaHook::hasMod);
                    break;
                case ARCLIGHT:
                    hooks.put("arclight", new ArclightHook());
                    break;
                case KETTING:
                    KettingHook kettingHook = new KettingHook();
                    hooks.put("ketting", kettingHook);
                    bukkitApi.setIsModLoaded(kettingHook::hasMod);
                    forgeApi.setIsModLoaded(kettingHook::hasMod);
                    break;
            }
        }
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param minecraftVersion The Minecraft version
     */
    public static void register(String minecraftVersion) {
        TaterAPIProvider.register(MinecraftVersion.from(minecraftVersion));
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     */
    public static void unregister() {
        TaterAPIProvider.apis.remove(apis.keySet().iterator().next());
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     * @param serverType The server type
     */
    public static void unregister(ServerType serverType) {
        TaterAPIProvider.apis.remove(serverType);
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE = "The API for %s hasn't loaded yet, or you don't have the TaterLib plugin installed.";

        NotLoadedException(ServerType serverType) {
            super(String.format(MESSAGE, serverType));
        }
    }
}
