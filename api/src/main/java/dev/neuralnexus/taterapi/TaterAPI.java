/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi;

import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.hooks.Hook;
import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.resource.impl.ResourceKeyImpl;
import dev.neuralnexus.taterapi.scheduler.Scheduler;
import dev.neuralnexus.taterapi.scheduler.SchedulerImpl;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterapi.server.metrics.TPSProvider;
import dev.neuralnexus.taterapi.storage.datastores.player.PlayerDataStore;
import dev.neuralnexus.taterapi.world.Location;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** API Provider */
public class TaterAPI {
    private static final Logger logger = Logger.create(LoaderImpl.PROJECT_ID);
    public static final UUID NIL_UUID = new UUID(0, 0);
    private static final List<Hook> hooks = new ArrayList<>();
    private static final Scheduler scheduler = new SchedulerImpl();
    private static PlayerDataStore playerDataStore;
    private static Supplier<String> serverName = () -> "MyMinecraftServer";
    private static final Map<Class<?>, Supplier<?>> factories = new HashMap<>();
    private static final Map<Class<?>, Supplier<?>> builders = new HashMap<>();
    private static final Map<Platform, Supplier<SimpleServer>> servers = new HashMap<>();
    private static final TaterAPI instance = new TaterAPI();
    private static boolean loaded = false;

    private TaterAPI() {
        registrySetup();
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static TaterAPI instance() {
        return instance;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static Logger logger() {
        return logger;
    }

    /**
     * Get if the API has loaded
     *
     * @return If the API has loaded
     */
    public static boolean hasLoaded() {
        return loaded;
    }

    /**
     * Set if the API has loaded
     *
     * @param loaded If the API has loaded
     */
    @ApiStatus.Internal
    public static void setLoaded(boolean loaded) {
        TaterAPI.loaded = loaded;
    }

    /**
     * Get the minecraft server
     *
     * @return The minecraft server
     */
    public SimpleServer server() {
        return server(MetaAPI.instance().primaryPlatform());
    }

    /**
     * Get the minecraft server
     *
     * @param platform The server type
     * @return The minecraft server
     */
    public SimpleServer server(Platform platform) {
        return servers.getOrDefault(platform, () -> (SimpleServer) MetaAPI.instance().minecraft())
                .get();
    }

    /**
     * Set the minecraftServer supplier
     *
     * @param minecraftServer The minecraftServer supplier
     */
    @ApiStatus.Internal
    public void setServer(Platform platform, Supplier<SimpleServer> minecraftServer) {
        servers.put(platform, minecraftServer);
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
     * Get a hook
     *
     * @param hookName The name of the hook
     * @param hookClass The class of the hook
     * @return The hook
     * @param <T> The hook type
     */
    public static <T> Optional<T> getHook(String hookName, Class<T> hookClass) {
        return hooks.stream()
                .filter(hook -> hook.name().equalsIgnoreCase(hookName))
                .map(hookClass::cast)
                .findFirst();
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

    @ApiStatus.Experimental
    @ApiStatus.Internal
    public static void setPlayerDataStore(PlayerDataStore playerDataStore) {
        TaterAPI.playerDataStore = playerDataStore;
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
    @ApiStatus.Experimental
    public static PlayerDataStore playerDataStore() {
        return playerDataStore;
    }

    /** Get the scheduler */
    public static Scheduler scheduler() {
        return scheduler;
    }

    /**
     * Get the UUID from a name
     *
     * @param name The name
     * @return The UUID
     */
    @ApiStatus.Experimental
    public static Optional<UUID> uuidFromName(String name) {
        return TaterAPI.instance().server().getPlayer(name).map(Identifiable::uuid);
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
        TaterAPI.serverName = serverName;
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

    // ---------------------------------------- Permissions ----------------------------------------

    /**
     * Check if a source has a permission
     *
     * @param permission The permission object
     * @return The predicate
     * @param <P> The permission type
     * @param <S> The subject type
     */
    public static <P, S> Predicate<S> hasPermission(P permission) {
        return PermsAPI.hasPermission(permission);
    }

    /**
     * Check Identifiable permissions
     *
     * @param identifiable The object to check
     * @param permissionLevel The permission
     * @return If the object has the permission
     * @throws NullPointerException If the object is null
     */
    public static boolean hasPermission(@NotNull Identifiable identifiable, int permissionLevel)
            throws NullPointerException {
        Objects.requireNonNull(identifiable, "Identifiable object cannot be null");
        return PermsAPI.instance().hasPermission(identifiable.uuid(), permissionLevel);
    }

    /**
     * Check Identifiable permissions
     *
     * @param identifiable The object to check
     * @param permission The permission
     * @return If the object has the permission
     * @throws NullPointerException If the object is null
     */
    public static boolean hasPermission(
            @NotNull Identifiable identifiable, @NotNull String permission)
            throws NullPointerException {
        Objects.requireNonNull(identifiable, "Identifiable object cannot be null");
        return PermsAPI.instance().hasPermission(identifiable.uuid(), permission);
    }

    /**
     * Check Identifiable permissions
     *
     * @param identifiable The object to check
     * @param permission The permission
     * @param defaultPermissionLevel The default permission level
     * @return If the object has the permission
     * @throws NullPointerException If the object or permission is null
     */
    public static boolean hasPermission(
            @NotNull Identifiable identifiable,
            @NotNull String permission,
            int defaultPermissionLevel)
            throws NullPointerException {
        Objects.requireNonNull(identifiable, "Identifiable object cannot be null");
        return PermsAPI.instance()
                .hasPermission(identifiable.uuid(), permission, defaultPermissionLevel);
    }

    /**
     * Check the subject's permissions
     *
     * @param subject The object to check
     * @param permissionLevel The permission
     * @return If the object has the permission
     * @throws NullPointerException If the object is null
     */
    public static boolean hasPermission(@NotNull Object subject, int permissionLevel)
            throws NullPointerException {
        if (subject instanceof Wrapped) {
            subject = ((Wrapped<?>) subject).unwrap();
        }
        return PermsAPI.instance().hasPermission(subject, permissionLevel);
    }

    /**
     * Check the subject's permissions
     *
     * @param subject The object to check
     * @param permission The permission
     * @return If the object has the permission
     * @throws NullPointerException If the object or permission is null
     */
    public static boolean hasPermission(@NotNull Object subject, @NotNull String permission)
            throws NullPointerException {
        if (subject instanceof Wrapped) {
            subject = ((Wrapped<?>) subject).unwrap();
        }
        return PermsAPI.instance().hasPermission(subject, permission);
    }

    /**
     * Check the subject's permissions
     *
     * @param subject The object to check
     * @param permission The permission
     * @param defaultPermissionLevel The default permission level
     * @return If the object has the permission
     * @throws NullPointerException If the object or permission is null
     */
    public static boolean hasPermission(
            @NotNull Object subject, @NotNull String permission, int defaultPermissionLevel)
            throws NullPointerException {
        if (subject instanceof Wrapped) {
            subject = ((Wrapped<?>) subject).unwrap();
        }
        return PermsAPI.instance().hasPermission(subject, permission, defaultPermissionLevel);
    }
}
