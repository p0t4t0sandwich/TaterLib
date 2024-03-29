package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.entity.Permissible;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.hooks.Hook;
import dev.neuralnexus.taterlib.hooks.hybrids.ArclightHook;
import dev.neuralnexus.taterlib.hooks.hybrids.KettingHook;
import dev.neuralnexus.taterlib.hooks.hybrids.MagmaHook;
import dev.neuralnexus.taterlib.hooks.hybrids.MohistHook;
import dev.neuralnexus.taterlib.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterlib.server.metrics.TPSProvider;
import dev.neuralnexus.taterlib.storage.datastores.player.PlayerDataStore;

import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Supplier;

/** API Provider */
public class TaterAPIProvider {
    private static final ServerType serverType = ServerType.serverType();
    private static final MinecraftVersion minecraftVersion = MinecraftVersion.minecraftVersion();
    private static final HashMap<ServerType, TaterAPI> apis = new HashMap<>();
    private static final List<Hook> hooks = new ArrayList<>();
    private static ServerType primaryServerType;
    private static PlayerDataStore playerDataStore;

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
     * Get TPSProvider hooks
     *
     * @return The TPSProvider hook
     */
    public static Optional<TPSProvider> getTPSProvider() {
        return hooks.stream()
                .filter(hook -> hook instanceof TPSProvider)
                .map(hook -> (TPSProvider) hook)
                .findFirst();
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

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED}
     *
     * @param pluginNameOrModId The name of the plugin or modId of the mod
     */
    public static boolean isPluginModLoaded(String pluginNameOrModId) {
        return apis.values().stream().anyMatch(api -> api.isPluginModLoaded(pluginNameOrModId));
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static boolean isPrimaryServerType(ServerType serverType) {
        return primaryServerType == serverType;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void setPrimaryServerType(ServerType serverType) {
        if (primaryServerType == null) {
            primaryServerType = serverType;
        }
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void setPlayerDataStore(PlayerDataStore playerDataStore) {
        TaterAPIProvider.playerDataStore = playerDataStore;
    }

    /**
     * Get the player data store <br>
     * Please note, this is for local storage only, and should not be used for cross-server data
     * storage. <br>
     * For cross-server data storage, use TODO: <b>ToBeNamed</b> <br>
     * Currently only supports primitive types, but you could use GSON to store the data as a string
     * first, then store it in the player data store.
     *
     * @return The player data store
     */
    public static PlayerDataStore playerDataStore() {
        return playerDataStore;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void register() {
        TaterAPI bukkitApi = new TaterAPI();
        TaterAPI bungeeApi = new TaterAPI();
        TaterAPI neoForgeApi = new TaterAPI();
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
                apis.put(ServerType.NEOFORGE, neoForgeApi);
            } else {
                apis.put(serverType, forgeApi);
                apis.put(ServerType.FORGE, forgeApi);
            }
        }

        // Check for SpongeForge, then Sponge
        if (serverType.isSpongeBased() && serverType.isForgeBased()) {
            TaterAPI spongeForgeApi = new TaterAPI();
            spongeForgeApi.setModList(() -> get(ServerType.FORGE).modList());
            apis.put(serverType, spongeForgeApi);
            apis.put(ServerType.SPONGE_FORGE, spongeForgeApi);
            apis.put(ServerType.SPONGE, spongeForgeApi);
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
            Supplier<List<PluginInfo>> bukkitPluginList = () -> get(ServerType.BUKKIT).pluginList();
            Supplier<List<ModInfo>> fabricModList = () -> get(ServerType.FABRIC).modList();
            Supplier<List<ModInfo>> forgeModList = () -> get(ServerType.FORGE).modList();
            Supplier<List<ModInfo>> neoForgeModList = () -> get(ServerType.NEOFORGE).modList();

            hybridApi.setPluginList(bukkitPluginList);
            if (serverType.isForgeHybrid()) {
                bukkitApi.setModList(forgeModList);
                forgeApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(forgeModList);
            } else if (serverType.isNeoForgeHybrid()) {
                bukkitApi.setModList(neoForgeModList);
                neoForgeApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(neoForgeModList);
            } else if (serverType.isFabricHybrid()) {
                bukkitApi.setModList(fabricModList);
                fabricApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(fabricModList);
            }

            switch (serverType) {
                case ARCLIGHT:
                    addHook(new ArclightHook());
                    apis.put(ServerType.ARCLIGHT, hybridApi);
                    break;
                case ARCLIGHT_NEO:
                    addHook(new ArclightHook());
                    apis.put(ServerType.ARCLIGHT_NEO, hybridApi);
                    break;
                case ARCLIGHT_FABRIC:
                    addHook(new ArclightHook());
                    apis.put(ServerType.ARCLIGHT_FABRIC, hybridApi);
                    break;
                case BANNER:
                    // TODO: check for Banner API
                    apis.put(ServerType.BANNER, hybridApi);
                    break;
                case CARDBOARD:
                    // TODO: check for Cardboard API
                    apis.put(ServerType.CARDBOARD, hybridApi);
                case KETTING:
                    addHook(new KettingHook());
                    apis.put(ServerType.KETTING, hybridApi);
                    break;
                case MAGMA:
                    addHook(new MagmaHook());
                    apis.put(ServerType.MAGMA, hybridApi);
                    break;
                case MOHIST:
                    addHook(new MohistHook());
                    apis.put(ServerType.MOHIST, hybridApi);
                    break;
                case MOHIST_NEO:
                    addHook(new MohistHook());
                    apis.put(ServerType.MOHIST_NEO, hybridApi);
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
