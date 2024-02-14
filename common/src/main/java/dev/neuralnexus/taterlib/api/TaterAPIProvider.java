package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.hooks.Hook;
import dev.neuralnexus.taterlib.hooks.hybrids.ArclightHook;
import dev.neuralnexus.taterlib.hooks.hybrids.KettingHook;
import dev.neuralnexus.taterlib.hooks.hybrids.MagmaHook;
import dev.neuralnexus.taterlib.hooks.hybrids.MohistHook;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;

import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/** API Provider */
public class TaterAPIProvider {
    private static final ServerType serverType = ServerType.serverType();
    private static final MinecraftVersion minecraftVersion = MinecraftVersion.getMinecraftVersion();
    private static final HashMap<ServerType, TaterAPI> apis = new HashMap<>();
    private static final Set<Hook> hooks = new HashSet<>();
    private static boolean eventListenersRegistered = false;

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
        return (minecraftVersion.isAtLeast(MinecraftVersion.V1_13)) || serverType.isVelocityBased();
    }

    /**
     * Add a hook
     *
     * @param hook The hook to add
     */
    public static void addHook(Hook hook) {
        hooks.add(hook);
    }

    /**
     * Get if a hook exists
     *
     * @param hookName The name of the hook
     */
    public static boolean isHooked(String hookName) {
        return hooks.stream().anyMatch(hook -> hook.name().equalsIgnoreCase(hookName));
    }

    /**
     * Get a hook
     *
     * @param hookName The name of the hook
     */
    public static Optional<Hook> getHook(String hookName) {
        return hooks.stream().filter(hook -> hook.name().equalsIgnoreCase(hookName)).findFirst();
    }

    /**
     * Remove a hook
     *
     * @param hookName The name of the hook
     */
    public static void removeHook(String hookName) {
        hooks.removeIf(hook -> hook.name().equalsIgnoreCase(hookName));
    }

    /**
     * Check Sender permissions
     *
     * @param permissible The entity to check
     * @param permission The permission
     */
    public static boolean hasPermission(Permissible permissible, String permission) {
        return hooks.stream()
                .filter(hook -> hook instanceof PermissionsHook)
                .anyMatch(hook -> ((PermissionsHook) hook).hasPermission(permissible, permission));
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
        return get(serverType);
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
    @ApiStatus.Internal
    public static boolean areEventListenersRegistered() {
        return eventListenersRegistered;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void setEventListenersRegistered(boolean registered) {
        eventListenersRegistered = registered;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void register() {
        TaterAPI bukkitApi = new TaterAPI();
        TaterAPI bungeeApi = new TaterAPI();
        TaterAPI forgeApi = new TaterAPI();
        TaterAPI fabricApi = new TaterAPI();

        if (serverType.isBukkitBased()) {
            apis.put(serverType, bukkitApi);
            apis.put(ServerType.BUKKIT, bukkitApi);
        }

        if (serverType.isBungeeCordBased()) {
            apis.put(serverType, bungeeApi);
            apis.put(ServerType.BUNGEECORD, bungeeApi);
        }

        // Secondary logical check is for Sinytra Connector
        if (serverType.isFabricBased() || (serverType.isForgeBased() && ServerType.isFabric())) {
            apis.put(serverType, fabricApi);
            apis.put(ServerType.FABRIC, fabricApi);
        }

        if (serverType.isForgeBased()) {
            if (serverType.is(ServerType.NEOFORGE)) {
                apis.put(ServerType.NEOFORGE, forgeApi);
            } else {
                apis.put(serverType, forgeApi);
                apis.put(ServerType.FORGE, forgeApi);
            }
        }

        // Check for SpongeForge, then Sponge
        if (serverType.isSpongeBased() && serverType.isForgeBased()) {
            apis.put(serverType, new TaterAPI());
            apis.put(ServerType.SPONGE_FORGE, new TaterAPI());
            apis.put(ServerType.SPONGE, new TaterAPI());
        } else if (serverType.isSpongeBased()) {
            apis.put(serverType, new TaterAPI());
            apis.put(ServerType.SPONGE_VANILLA, new TaterAPI());
            apis.put(ServerType.SPONGE, new TaterAPI());
        }

        if (serverType.isVelocityBased()) {
            apis.put(ServerType.VELOCITY, new TaterAPI());
        }

        if (serverType.isHybrid()) {
            TaterAPI hybridApi = new TaterAPI();

            switch (serverType) {
                case MOHIST:
                    addHook(new MohistHook());
                    bukkitApi.setModList(() -> get(ServerType.FORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.FORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.MOHIST, hybridApi);
                    break;
                case MOHIST_NEO:
                    addHook(new MohistHook());
                    bukkitApi.setModList(() -> get(ServerType.NEOFORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.NEOFORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.MOHIST, hybridApi);
                    break;
                case MAGMA:
                    MagmaHook magmaHook = new MagmaHook();
                    addHook(magmaHook);
                    bukkitApi.setModList(() -> get(ServerType.FORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.FORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.MAGMA, hybridApi);
                    break;
                case ARCLIGHT:
                    addHook(new ArclightHook());
                    bukkitApi.setModList(() -> get(ServerType.FORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.FORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.ARCLIGHT, hybridApi);
                    break;
                case ARCLIGHT_NEO:
                    addHook(new ArclightHook());
                    bukkitApi.setModList(() -> get(ServerType.NEOFORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.NEOFORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.ARCLIGHT, hybridApi);
                    break;
                case KETTING:
                    KettingHook kettingHook = new KettingHook();
                    addHook(kettingHook);
                    bukkitApi.setModList(() -> get(ServerType.FORGE).modList());
                    forgeApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    hybridApi.setModList(() -> get(ServerType.FORGE).modList());
                    hybridApi.setPluginList(() -> get(ServerType.BUKKIT).pluginList());
                    apis.put(ServerType.KETTING, hybridApi);
                    break;
            }
        }
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void unregister() {
        apis.remove(serverType);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param serverType The server type
     */
    @ApiStatus.Internal
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
