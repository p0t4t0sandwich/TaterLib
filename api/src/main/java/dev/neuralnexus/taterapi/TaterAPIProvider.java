/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.taterapi.entity.Permissible;
import dev.neuralnexus.taterapi.hooks.Hook;
import dev.neuralnexus.taterapi.hooks.permissions.PermissionsHook;
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
    private static final HashMap<Platform, TaterAPI> apis = new HashMap<>();
    private static final List<Hook> hooks = new ArrayList<>();
    @Deprecated private static Side side = Side.SERVER;
    private static final Scheduler scheduler = new SchedulerImpl();
    private static PlayerDataStore playerDataStore;
    private static Supplier<String> serverName = () -> "MyMinecraftServer";
    private static final Map<Class<?>, Supplier<?>> factories = new HashMap<>();
    private static final Map<Class<?>, Supplier<?>> builders = new HashMap<>();

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
    @Deprecated
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
        return api(MetaAPI.instance().primaryPlatform());
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
