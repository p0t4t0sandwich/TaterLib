package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.impl.metadata.PlatformDataImpl;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
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
    private static final Platform platform = Platform.get();
    private static final PlatformData platformData = new PlatformDataImpl();
    private static final MinecraftVersion minecraftVersion = MinecraftVersion.get();
    private static final HashMap<Platform, TaterAPI> apis = new HashMap<>();
    private static final List<Hook> hooks = new ArrayList<>();
    private static Platform primaryPlatform;
    private static PlayerDataStore playerDataStore;
    private static Supplier<String> serverName = () -> "MyMinecraftServer";

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
    public static Platform platform() {
        return platform;
    }

    /**
     * Get platform data
     *
     * @return The platform data
     */
    public static PlatformData platformData() {
        return platformData;
    }

    /**
     * Whether Brigadier is supported
     *
     * @return If Brigadier is supported
     */
    public static boolean isBrigadierSupported() {
        return (minecraftVersion.isAtLeast(MinecraftVersion.V1_13))
                || platform.isVelocityBased();
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
        return get(platform);
    }

    /**
     * Get the instance of the API for a specific server type
     *
     * @param platform The server type
     * @return The instance of the API
     */
    public static TaterAPI get(Platform platform) {
        if (apis.containsKey(platform)) {
            return apis.get(platform);
        }
        throw new NotLoadedException(platform);
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
    public static boolean isPrimaryServerType(Platform platform) {
        return primaryPlatform == platform;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void setPrimaryServerType(Platform platform) {
        if (primaryPlatform == null) {
            primaryPlatform = platform;
        }
    }

    /**
     * Get the server name
     *
     * @return The server name
     */
    public static String serverName() {
        return serverName.get();
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

        if (platform.isBukkitBased()) {
            apis.put(platform, bukkitApi);
            apis.put(Platform.BUKKIT, bukkitApi);
        }

        if (platform.isBungeeCordBased()) {
            apis.put(platform, bungeeApi);
            apis.put(Platform.BUNGEECORD, bungeeApi);
        }

        // Secondary logical check is for Sinytra Connector
        if (platform.isFabricBased() || (platform.isForgeBased() && Platform.isFabric())) {
            apis.put(platform, fabricApi);
            apis.put(Platform.FABRIC, fabricApi);
        }

        if (platform.isForgeBased()) {
            if (platform.is(Platform.NEOFORGE)) {
                apis.put(Platform.NEOFORGE, neoForgeApi);
            } else {
                apis.put(platform, forgeApi);
                apis.put(Platform.FORGE, forgeApi);
            }
        }

        // Check for SpongeForge, then Sponge
        if (platform.isSpongeBased() && platform.isForgeBased()) {
            TaterAPI spongeForgeApi = new TaterAPI();
            spongeForgeApi.setModList(() -> get(Platform.FORGE).modList());
            apis.put(platform, spongeForgeApi);
            apis.put(Platform.SPONGE_FORGE, spongeForgeApi);
            apis.put(Platform.SPONGE, spongeForgeApi);
        } else if (platform.isSpongeBased()) {
            apis.put(platform, new TaterAPI());
            apis.put(Platform.SPONGE_VANILLA, new TaterAPI());
            apis.put(Platform.SPONGE, new TaterAPI());
        }

        if (platform.isVelocityBased()) {
            apis.put(Platform.VELOCITY, new TaterAPI());
        }

        if (platform.isHybrid()) {
            TaterAPI hybridApi = new TaterAPI();
            Supplier<List<PluginInfo>> bukkitPluginList = () -> get(Platform.BUKKIT).pluginList();
            Supplier<List<ModInfo>> fabricModList = () -> get(Platform.FABRIC).modList();
            Supplier<List<ModInfo>> forgeModList = () -> get(Platform.FORGE).modList();
            Supplier<List<ModInfo>> neoForgeModList = () -> get(Platform.NEOFORGE).modList();

            hybridApi.setPluginList(bukkitPluginList);
            if (platform.isForgeHybrid()) {
                bukkitApi.setModList(forgeModList);
                forgeApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(forgeModList);
            } else if (platform.isNeoForgeHybrid()) {
                bukkitApi.setModList(neoForgeModList);
                neoForgeApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(neoForgeModList);
            } else if (platform.isFabricHybrid()) {
                bukkitApi.setModList(fabricModList);
                fabricApi.setPluginList(bukkitPluginList);
                hybridApi.setModList(fabricModList);
            }

            switch (platform) {
                case ARCLIGHT:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT, hybridApi);
                    break;
                case ARCLIGHT_NEO:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT_NEO, hybridApi);
                    break;
                case ARCLIGHT_FABRIC:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT_FABRIC, hybridApi);
                    break;
                case BANNER:
                    // TODO: check for Banner API
                    apis.put(Platform.BANNER, hybridApi);
                    break;
                case CARDBOARD:
                    // TODO: check for Cardboard API
                    apis.put(Platform.CARDBOARD, hybridApi);
                case KETTING:
                    addHook(new KettingHook());
                    apis.put(Platform.KETTING, hybridApi);
                    break;
                case MAGMA:
                    addHook(new MagmaHook());
                    apis.put(Platform.MAGMA, hybridApi);
                    break;
                case MOHIST:
                    addHook(new MohistHook());
                    apis.put(Platform.MOHIST, hybridApi);
                    break;
                case MOHIST_NEO:
                    addHook(new MohistHook());
                    apis.put(Platform.MOHIST_NEO, hybridApi);
                    break;
            }
        }
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void unregister() {
        apis.remove(platform);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param platform The server type
     */
    @ApiStatus.Internal
    public static void unregister(Platform platform) {
        apis.remove(platform);
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY <br>
     * Set the serverName supplier
     *
     * @param serverName The serverName supplier
     */
    @ApiStatus.Internal
    public void setModLoaderVersion(Supplier<String> serverName) {
        TaterAPIProvider.serverName = serverName;
    }

    /**
     * Throw this exception when the API hasn't loaded yet, or you don't have the plugin installed.
     */
    private static final class NotLoadedException extends IllegalStateException {
        private static final String MESSAGE =
                "The API for %s hasn't loaded yet, or you don't have the TaterLib plugin installed.";

        NotLoadedException(Platform serverType) {
            super(String.format(MESSAGE, serverType));
        }
    }
}
