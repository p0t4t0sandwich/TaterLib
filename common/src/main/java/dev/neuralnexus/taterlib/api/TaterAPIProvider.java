package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.hooks.ArclightHook;
import dev.neuralnexus.taterlib.hooks.KettingHook;
import dev.neuralnexus.taterlib.hooks.MagmaHook;
import dev.neuralnexus.taterlib.hooks.MohistHook;

import java.util.HashMap;

/** API Provider */
public class TaterAPIProvider {
    private static final ServerType serverType = ServerType.getServerType();
    private static final HashMap<ServerType, TaterAPI> apis = new HashMap<>();
    private static final HashMap<String, Object> hooks = new HashMap<>();
    private static MinecraftVersion minecraftVersion = MinecraftVersion.UNKNOWN;

    /**
     * Set the Minecraft version DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param minecraftVersion The Minecraft version
     */
    private static void setMinecraftVersion(MinecraftVersion minecraftVersion) {
        TaterAPIProvider.minecraftVersion = minecraftVersion;
    }

    /**
     * Get Minecraft version
     *
     * @return The Minecraft version
     */
    public static MinecraftVersion minecraftVersion() {
        return minecraftVersion;
    }

    /**
     * Get server type
     *
     * @return The server type
     */
    public static ServerType serverType() {
        return serverType;
    }

    /**
     * Whether Brigadier is supported
     *
     * @return If Brigadier is supported
     */
    public static boolean isBrigadierSupported() {
        return (minecraftVersion.isAtLeast(MinecraftVersion.V1_13)
                        && (serverType.isForgeBased() || serverType.isFabricBased()))
                || serverType.isVelocityBased();
    }

    /**
     * Add a hook
     *
     * @param hookName The name of the hook
     * @param hook The hook to add
     */
    public static void addHook(String hookName, Object hook) {
        hooks.put(hookName.toLowerCase(), hook);
    }

    /**
     * Get if a hook exists
     *
     * @param hookName The name of the hook
     */
    public static boolean isHooked(String hookName) {
        return hooks.containsKey(hookName.toLowerCase());
    }

    /**
     * Get a hook
     *
     * @param hookName The name of the hook
     */
    public static Object getHook(String hookName) {
        return hooks.get(hookName.toLowerCase());
    }

    /**
     * Remove a hook
     *
     * @param hookName The name of the hook
     */
    public static void removeHook(String hookName) {
        hooks.remove(hookName.toLowerCase());
    }

    /**
     * Get the instance of the API
     *
     * @return The instance of the API
     */
    public static TaterAPI get() {
        if (apis.isEmpty()) {
            register();
        }
        return apis.get(serverType);
    }

    /**
     * Get the instance of the API for a specific server type
     *
     * @param serverType The server type
     * @return The instance of the API
     */
    public static TaterAPI get(ServerType serverType) {
        if (apis.containsKey(serverType)) {
            return apis.get(serverType);
        }
        throw new NotLoadedException(serverType);
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    public static void register() {
        ServerType serverType = ServerType.getServerType();

        if (serverType.isBukkitBased()) {
            apis.put(ServerType.BUKKIT, new TaterAPI("plugins"));
        }

        if (serverType.isBungeeCordBased()) {
            apis.put(ServerType.BUNGEECORD, new TaterAPI("plugins"));
        }

        if (serverType.isFabricBased()) {
            apis.put(ServerType.FABRIC, new TaterAPI("config"));
        }

        if (serverType.is(ServerType.NEOFORGE)) {
            apis.put(ServerType.NEOFORGE, new TaterAPI("config"));
        } else if (serverType.isForgeBased()) {
            apis.put(ServerType.FORGE, new TaterAPI("config"));
        }

        if (serverType.isSpongeBased()) {
            apis.put(ServerType.SPONGE, new TaterAPI("config"));
        }

        if (serverType.isVelocityBased()) {
            apis.put(ServerType.VELOCITY, new TaterAPI("plugins"));
        }

        if (serverType.isHybrid()) {
            TaterAPI bukkitApi = apis.get(ServerType.BUKKIT);
            TaterAPI forgeApi = apis.get(ServerType.FORGE);

            // Sets defaults, so it might give a proper result for hybrids that don't have hooks
            TaterAPI hybridApi = new TaterAPI("config");
            hybridApi.setIsModLoaded((modid) -> apis.get(ServerType.FORGE).isModLoaded(modid));
            hybridApi.setIsPluginLoaded(
                    (plugin) -> apis.get(ServerType.BUKKIT).isPluginLoaded(plugin));

            switch (serverType) {
                case MOHIST:
                    MohistHook mohistHook = new MohistHook();
                    addHook("mohist", mohistHook);
                    bukkitApi.setIsModLoaded(mohistHook::hasMod);
                    bukkitApi.setIsPluginLoaded(mohistHook::hasPlugin);
                    forgeApi.setIsModLoaded(mohistHook::hasMod);
                    forgeApi.setIsPluginLoaded(mohistHook::hasPlugin);
                    hybridApi.setIsModLoaded(mohistHook::hasMod);
                    hybridApi.setIsPluginLoaded(mohistHook::hasPlugin);
                    apis.put(ServerType.MOHIST, hybridApi);
                    break;
                case MAGMA:
                    MagmaHook magmaHook = new MagmaHook();
                    addHook("magma", magmaHook);
                    bukkitApi.setIsModLoaded(magmaHook::hasMod);
                    forgeApi.setIsModLoaded(magmaHook::hasMod);
                    hybridApi.setIsModLoaded(magmaHook::hasMod);
                    apis.put(ServerType.MAGMA, hybridApi);
                    break;
                case ARCLIGHT:
                    addHook("arclight", new ArclightHook());
                    apis.put(ServerType.ARCLIGHT, hybridApi);
                    break;
                case KETTING:
                    KettingHook kettingHook = new KettingHook();
                    addHook("ketting", kettingHook);
                    bukkitApi.setIsModLoaded(kettingHook::hasMod);
                    bukkitApi.setIsPluginLoaded(kettingHook::hasPlugin);
                    forgeApi.setIsModLoaded(kettingHook::hasMod);
                    forgeApi.setIsPluginLoaded(kettingHook::hasPlugin);
                    hybridApi.setIsModLoaded(kettingHook::hasMod);
                    hybridApi.setIsPluginLoaded(kettingHook::hasPlugin);
                    apis.put(ServerType.KETTING, hybridApi);
                    break;
            }
        }
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param minecraftVersion The Minecraft version
     */
    public static void register(String minecraftVersion) {
        setMinecraftVersion(MinecraftVersion.from(minecraftVersion));
        register();
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    public static void unregister() {
        apis.remove(serverType);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param serverType The server type
     */
    public static void unregister(ServerType serverType) {
        apis.remove(serverType);
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE =
                "The API for %s hasn't loaded yet, or you don't have the TaterLib plugin installed.";

        NotLoadedException(ServerType serverType) {
            super(String.format(MESSAGE, serverType));
        }
    }
}
