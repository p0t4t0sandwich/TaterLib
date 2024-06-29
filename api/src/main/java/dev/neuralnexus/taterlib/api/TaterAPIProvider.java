/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.api;

import dev.neuralnexus.taterlib.api.impl.metadata.*;
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
        return MinecraftVersion.get();
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
        return api().orElseThrow(() -> new NotLoadedException(platform)).platformData();
    }

    /**
     * Whether Brigadier is supported
     *
     * @return If Brigadier is supported
     */
    public static boolean isBrigadierSupported() {
        return (minecraftVersion().isAtLeast(MinecraftVersion.V1_13)) || platform.isVelocityBased();
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
    public static Optional<TaterAPI> api() {
        if (apis.isEmpty()) {
            register();
        }
        return api(platform);
    }

    /**
     * Get the instance of the API for a specific server type
     *
     * @param platform The server type
     * @return The instance of the API
     */
    public static Optional<TaterAPI> api(Platform platform) {
        return Optional.ofNullable(apis.get(platform));
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED}
     *
     * @param pluginNameOrModId The name of the plugin or modId of the mod
     */
    public static boolean isPluginModLoaded(String pluginNameOrModId) {
        return apis.values().stream().anyMatch(api -> api.isModLoaded(pluginNameOrModId));
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static boolean isPrimaryPlatform(Platform platform) {
        return primaryPlatform == platform;
    }

    /** DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY */
    @ApiStatus.Internal
    public static void setPrimaryPlatform(Platform platform) {
        if (primaryPlatform == null) {
            primaryPlatform = platform;
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
        if (platform.isBukkitBased()) {
            TaterAPI bukkitApi = new TaterAPI(new BukkitData());
            apis.put(platform, bukkitApi);
            apis.put(Platform.BUKKIT, bukkitApi);
        }

        if (platform.isBungeeCordBased()) {
            TaterAPI bungeeCordApi = new TaterAPI(new BungeeCordData());
            apis.put(platform, bungeeCordApi);
            apis.put(Platform.BUNGEECORD, bungeeCordApi);
        }

        // Secondary logical check is for Sinytra Connector
        if (platform.isFabricBased() || (platform.isForgeBased() && Platform.isFabric())) {
            TaterAPI fabricApi = new TaterAPI(new FabricData());
            apis.put(platform, fabricApi);
            apis.put(Platform.FABRIC, fabricApi);
        }

        if (platform.isForgeBased()) {
            if (platform.is(Platform.NEOFORGE)) {
                apis.put(Platform.NEOFORGE, new TaterAPI(new NeoForgeData()));
            } else {
                TaterAPI forgeApi = new TaterAPI(new ForgeData());
                apis.put(platform, forgeApi);
                apis.put(Platform.FORGE, forgeApi);
            }
        }

        // Check for SpongeForge, then Sponge
        if (platform.isSpongeBased() && platform.isForgeBased()) {
            TaterAPI spongeForgeApi = new TaterAPI(new SpongeData(), new ForgeData());
            apis.put(platform, spongeForgeApi);
            apis.put(Platform.SPONGE_FORGE, spongeForgeApi);
            apis.put(Platform.SPONGE, new TaterAPI(new SpongeData()));
        } else if (platform.isSpongeBased()) {
            TaterAPI spongeAPI = new TaterAPI(new SpongeData());
            apis.put(platform, spongeAPI);
            apis.put(Platform.SPONGE_VANILLA, spongeAPI);
            apis.put(Platform.SPONGE, spongeAPI);
        }

        if (platform.isVelocityBased()) {
            apis.put(Platform.VELOCITY, new TaterAPI(new VelocityData()));
        }

        if (platform.isHybrid()) {
            TaterAPI forgeHybrid = new TaterAPI(new BukkitData(), new ForgeData());
            TaterAPI neoForgeHybrid = new TaterAPI(new BukkitData(), new NeoForgeData());
            TaterAPI fabricHybrid = new TaterAPI(new BukkitData(), new FabricData());

            switch (platform) {
                case ARCLIGHT:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT, forgeHybrid);
                    break;
                case ARCLIGHT_NEO:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT_NEO, neoForgeHybrid);
                    break;
                case ARCLIGHT_FABRIC:
                    addHook(new ArclightHook());
                    apis.put(Platform.ARCLIGHT_FABRIC, fabricHybrid);
                    break;
                case BANNER:
                    // TODO: check for Banner API
                    apis.put(Platform.BANNER, fabricHybrid);
                    break;
                case CARDBOARD:
                    // TODO: check for Cardboard API
                    apis.put(Platform.CARDBOARD, fabricHybrid);
                case KETTING:
                    addHook(new KettingHook());
                    apis.put(Platform.KETTING, forgeHybrid);
                    break;
                case MAGMA:
                    addHook(new MagmaHook());
                    apis.put(Platform.MAGMA, forgeHybrid);
                    break;
                case MOHIST:
                    addHook(new MohistHook());
                    apis.put(Platform.MOHIST, forgeHybrid);
                    break;
                case MOHIST_NEO:
                    addHook(new MohistHook());
                    apis.put(Platform.MOHIST_NEO, neoForgeHybrid);
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
     * Get the server name
     *
     * @return The server name
     */
    public static String serverName() {
        return serverName.get();
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY <br>
     * Set the serverName supplier
     *
     * @param serverName The serverName supplier
     */
    @ApiStatus.Internal
    public void setServerName(Supplier<String> serverName) {
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
