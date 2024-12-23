/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi;

import static dev.neuralnexus.taterapi.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.hooks.Hook;
import dev.neuralnexus.taterapi.hooks.hybrids.ArclightHook;
import dev.neuralnexus.taterapi.hooks.hybrids.KettingHook;
import dev.neuralnexus.taterapi.hooks.hybrids.MagmaHook;
import dev.neuralnexus.taterapi.hooks.hybrids.MohistHook;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
import dev.neuralnexus.taterapi.metadata.PlatformData;
import dev.neuralnexus.taterapi.metadata.impl.*;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.resource.impl.ResourceKeyImpl;
import dev.neuralnexus.taterapi.scheduler.Scheduler;
import dev.neuralnexus.taterapi.scheduler.SchedulerImpl;
import dev.neuralnexus.taterapi.server.metrics.TPSProvider;
import dev.neuralnexus.taterapi.storage.datastores.player.PlayerDataStore;
import dev.neuralnexus.taterapi.world.Location;

import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Supplier;

/** API Provider */
@SuppressWarnings("unused")
public class TaterAPIProvider {
    @Deprecated private static final Platform platform = Platform.get();
    private static final HashMap<Platform, TaterAPI> apis = new HashMap<>();
    private static final List<Hook> hooks = new ArrayList<>();
    @Deprecated private static Platform primaryPlatform;
    @Deprecated private static Side side = Side.SERVER;
    private static final Scheduler scheduler = new SchedulerImpl();
    private static PlayerDataStore playerDataStore;
    private static Supplier<String> serverName = () -> "MyMinecraftServer";
    private static final Map<Class<?>, Supplier<?>> factories = new HashMap<>();
    private static final Map<Class<?>, Supplier<?>> builders = new HashMap<>();

    /**
     * Get Minecraft version
     *
     * @return The Minecraft version
     */
    @Deprecated
    public static MinecraftVersion minecraftVersion() {
        return MinecraftVersion.get();
    }

    /**
     * Get server type
     *
     * @return The server type
     */
    @Deprecated
    public static Platform platform() {
        return platform;
    }

    /**
     * Get platform data
     *
     * @return The platform data
     */
    @Deprecated
    public static PlatformData platformData() {
        return api().orElseThrow(() -> new NotLoadedException(platform)).platformData();
    }

    /**
     * Whether Brigadier is supported
     *
     * @return If Brigadier is supported
     */
    @Deprecated
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
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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
        if (platform != primaryPlatform) {
            return api(primaryPlatform);
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
        if (apis.isEmpty()) {
            register();
        }
        return Optional.ofNullable(apis.get(platform));
    }

    /**
     * Get if a plugin/mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started {@link ServerEvents#STARTED}
     *
     * @param nameOrId The name of the plugin or modId of the mod
     */
    @Deprecated
    public static boolean isModLoaded(String nameOrId) {
        return apis.values().stream().anyMatch(api -> api.isModLoaded(nameOrId));
    }

    @Deprecated
    public static Platform primaryPlatform() {
        return primaryPlatform;
    }

    @ApiStatus.Internal
    @Deprecated
    public static boolean isPrimaryPlatform(Platform platform) {
        return primaryPlatform == platform;
    }

    @ApiStatus.Internal
    @Deprecated
    public static void setPrimaryPlatform(Platform platform) {
        if (primaryPlatform == null) {
            primaryPlatform = platform;
        }
    }

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

    @ApiStatus.Internal
    public static void register() {
        registrySetup();

        Set<PlatformData> activeDatas = new HashSet<>();
        PlatformData bukkitData = new BukkitData();
        PlatformData bungeeCordData = new BungeeCordData();
        PlatformData fabricData = new FabricData();
        PlatformData neoForgeData = new NeoForgeData();
        PlatformData forgeData = ForgeData.create();
        PlatformData spongeData = SpongeData.create();
        PlatformData velocityData = new VelocityData();

        if (platform.isBukkitBased()) {
            activeDatas.add(bukkitData);
            apis.put(Platform.BUKKIT, new TaterAPI(bukkitData));
        }

        if (platform.isBungeeCordBased()) {
            activeDatas.add(bungeeCordData);
            apis.put(Platform.BUNGEECORD, new TaterAPI(bungeeCordData));
        }

        // Secondary logical check is for Sinytra Connector
        if (platform.isFabricBased() || checkForClass("org.sinytra.connector.mod.ConnectorMod")) {
            activeDatas.add(fabricData);
            apis.put(Platform.FABRIC, new TaterAPI(fabricData));
        }

        // Secondary logical check is for Kilt
        if (platform.isNeoForgeBased() || checkForClass("org.kitteh.kilt.Kilt")) {
            activeDatas.add(neoForgeData);
            apis.put(Platform.NEOFORGE, new TaterAPI(neoForgeData));
        }
        // NeoForge is technically Forge-based, so Forge is in the same logic block
        else if (platform.isForgeBased()) {
            activeDatas.add(forgeData);
            apis.put(Platform.FORGE, new TaterAPI(forgeData));
        }

        // Check for SpongeForge, then Sponge
        if (platform.isSpongeBased()) {
            activeDatas.add(spongeData);
            TaterAPI spongeAPI = new TaterAPI(spongeData);
            apis.put(Platform.SPONGE, spongeAPI);
            if (platform.isForgeBased()) {
                apis.put(Platform.SPONGE_FORGE, new TaterAPI(spongeData, forgeData));
            } else {
                apis.put(Platform.SPONGE_VANILLA, spongeAPI);
            }
        }

        if (platform.isVelocityBased()) {
            activeDatas.add(velocityData);
            apis.put(Platform.VELOCITY, new TaterAPI(velocityData));
        }

        apis.put(platform, new TaterAPI(activeDatas.toArray(new PlatformData[0])));

        if (platform.isHybrid()) {
            switch (platform) {
                case ARCLIGHT:
                case ARCLIGHT_FABRIC:
                case ARCLIGHT_NEO:
                    addHook(new ArclightHook());
                    break;
                case BANNER:
                    // TODO: check for Banner API
                    break;
                case CARDBOARD:
                    // TODO: check for Cardboard API
                case KETTING:
                    addHook(new KettingHook());
                    break;
                case MAGMA:
                    addHook(new MagmaHook());
                    break;
                case MOHIST:
                case MOHIST_NEO:
                    addHook(new MohistHook());
                    break;
            }
        }
    }

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

    /** Get the "side" the mod is running on */
    @Deprecated
    public static Side side() {
        return TaterAPIProvider.side;
    }

    /**
     * DO NOT USE THIS METHOD, IT IS FOR INTERNAL USE ONLY
     *
     * @param side The side
     */
    @ApiStatus.Internal
    @Deprecated
    public static void setSide(Side side) {
        TaterAPIProvider.side = side;
    }

    /** Get the scheduler */
    public static Scheduler scheduler() {
        return scheduler;
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

    @ApiStatus.Internal
    public static void registrySetup() {
        registerBuilder(Location.Builder.class, () -> null);
        registerBuilder(ResourceKey.Builder.class, ResourceKeyImpl.Builder::new);
        registerFactory(ResourceKey.Factory.class, ResourceKeyImpl.Factory::new);
    }

    /**
     * Register a factory
     *
     * @param clazz The class
     * @param factory The factory
     */
    public static <T> void registerFactory(Class<T> clazz, Supplier<T> factory) {
        factories.put(clazz, factory);
    }

    /**
     * Get a factory
     *
     * @param clazz The class
     * @return The factory
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFactory(Class<T> clazz) {
        return (T) factories.get(clazz).get();
    }

    /**
     * Register a builder
     *
     * @param clazz The class
     * @param builder The builder
     */
    public static <T> void registerBuilder(Class<T> clazz, Supplier<T> builder) {
        builders.put(clazz, builder);
    }

    /**
     * Get a builder
     *
     * @param clazz The class
     * @return The builder
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBuilder(Class<T> clazz) {
        return (T) builders.get(clazz).get();
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
