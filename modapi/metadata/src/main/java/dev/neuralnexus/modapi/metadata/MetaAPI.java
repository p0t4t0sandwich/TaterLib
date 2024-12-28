/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.MetaAPIImpl;
import dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/** Interface for accessing the metadata cache and other useful shortcuts. */
public interface MetaAPI {
    /** Get the instance of the MetaAPI */
    static MetaAPI instance() {
        return MetaAPIImpl.getInstance();
    }

    // ----------------------------- Platform -----------------------------

    /**
     * Get the primary platform that the environment is running
     *
     * @return The platform
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     */
    Platform primaryPlatform() throws NoPrimaryPlatformException;

    /**
     * Set the primary platform that the environment is running
     *
     * @param platform The platform
     * @throws RedefinePrimaryPlatformException if the primary platform is already defined
     * @throws NullPointerException if the platform is null
     */
    void setPrimaryPlatform(@NotNull Platform platform)
            throws RedefinePrimaryPlatformException, NullPointerException;

    /**
     * Check if a platform is the same as the one identified as the primary platform
     *
     * @param platform The platform to check
     * @return True, if they match, false otherwise
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     * @throws NullPointerException if the platform is null
     */
    boolean isPrimaryPlatform(@NotNull Platform platform)
            throws NoPrimaryPlatformException, NullPointerException;

    /**
     * Get the platform the environment is running, returns the primary platform, or the first
     * platform in the list of detected platforms. Essentially a more lenient alternative to {@link
     * MetaAPI#primaryPlatform()}
     *
     * @return The platform
     * @throws NoPlatformException if there is no platform detected
     */
    Platform platform() throws NoPlatformException;

    /**
     * Check if a platform is present
     *
     * @param platform The platform
     * @return True if the platform is present, false otherwise
     * @throws NullPointerException if the platform is null
     */
    boolean isPlatformPresent(@NotNull Platform platform) throws NullPointerException;

    /**
     * Check if any of the given platforms are present
     *
     * @param platform The platforms
     * @return True if any platform is present, false otherwise
     * @throws NullPointerException if the platform is null
     */
    default boolean isPlatformPresent(@NotNull Platform... platform) throws NullPointerException {
        for (Platform p : platform) {
            if (this.isPlatformPresent(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all the given platforms are present
     *
     * @param platform The platforms
     * @return True if all platforms are present, false otherwise
     * @throws NullPointerException if the platform is null
     */
    default boolean allPlatformsPresent(@NotNull Platform... platform) throws NullPointerException {
        for (Platform p : platform) {
            if (!this.isPlatformPresent(p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the metadata for the primary platform
     *
     * @return The Platform's metadata
     * @throws NoPlatformException if the primary platform is not detected
     * @throws NoPlatformMetaException if there's no metadata for the platform
     */
    Platform.Meta meta() throws NoPlatformException, NoPlatformMetaException;

    /**
     * Get the metadata for the specified platform
     *
     * @param platform The Platform
     * @return The Platform's metadata
     * @throws NullPointerException if the platform is null
     */
    Optional<Platform.Meta> meta(@NotNull Platform platform) throws NullPointerException;

    // ----------------------------- Platform.Meta Getters -----------------------------

    /**
     * Get the version of Minecraft the server is running
     *
     * @return The current Minecraft version
     */
    MinecraftVersion version();

    /**
     * Get if a mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started
     *
     * @param nameOrId The name of the plugin or modId of the mod
     * @return True if the mod is loaded, false otherwise
     * @throws NullPointerException if the nameOrId is null
     */
    boolean isModLoaded(@NotNull String nameOrId) throws NullPointerException;

    /**
     * Get if a mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started
     *
     * @param platform The platform
     * @param nameOrId The name of the plugin or modId of the mod
     * @return True if the mod is loaded, false otherwise
     * @throws NullPointerException if the platform or nameOrId is null
     */
    Optional<Boolean> isModLoaded(@NotNull Platform platform, @NotNull String nameOrId)
            throws NullPointerException;

    /**
     * Get the platform's mappings
     *
     * @return The platform's mappings
     */
    Mappings mappings();

    /**
     * Get a platform's mappings
     *
     * @param platform The platform
     * @return The platform's mappings
     * @throws NullPointerException if the platform is null
     */
    Optional<Mappings> mappings(@NotNull Platform platform) throws NullPointerException;

    /**
     * Get a new logger for the specified modId
     *
     * @param modId The mod id
     * @return A new Logger
     * @throws NullPointerException if the modId is null
     */
    Logger logger(@NotNull String modId) throws NullPointerException;

    // ----------------------------- Platform Checks -----------------------------

    /**
     * Check if the platform is SpongeForge
     *
     * @return True if the platform is SpongeForge, false otherwise
     */
    default boolean isSpongeForge() {
        return this.allPlatformsPresent(Platforms.SPONGE, Platforms.FORGE);
    }

    /**
     * Check if the platform is SpongeFabric
     *
     * @return True if the platform is SpongeFabric, false otherwise
     */
    default boolean isSpongeFabric() {
        return this.allPlatformsPresent(Platforms.SPONGE, Platforms.FABRIC);
    }

    /**
     * Check if the platform is a proxy
     *
     * @return True if the platform is a proxy, false otherwise
     */
    default boolean isProxy() {
        return this.isPlatformPresent(Platforms.BUNGEECORD, Platforms.VELOCITY);
    }

    /**
     * Check if the platform is a Forge hybrid
     *
     * @return True if the platform is a Forge hybrid, false otherwise
     */
    default boolean isForgeHybrid() {
        return this.allPlatformsPresent(Platforms.FORGE, Platforms.BUKKIT);
    }

    /**
     * Check if the platform is a Fabric hybrid
     *
     * @return True if the platform is a Fabric hybrid, false otherwise
     */
    default boolean isFabricHybrid() {
        return this.allPlatformsPresent(Platforms.FABRIC, Platforms.BUKKIT);
    }

    /**
     * Check if the platform is a NeoForge hybrid
     *
     * @return True if the platform is a NeoForge hybrid, false otherwise
     */
    default boolean isNeoForgeHybrid() {
        return this.allPlatformsPresent(Platforms.NEOFORGE, Platforms.BUKKIT);
    }

    /**
     * Check if the platform is a hybrid
     *
     * @return True if the platform is a hybrid, false otherwise
     */
    default boolean isHybrid() {
        return this.isForgeHybrid() || this.isFabricHybrid() || this.isNeoForgeHybrid();
    }

    /**
     * Check if the platform is a mixed Forge/Fabric environment
     *
     * @return True if the platform is a mixed Forge/Fabric environment, false otherwise
     */
    default boolean isMixedForgeFabric() {
        return this.allPlatformsPresent(Platforms.FORGE, Platforms.FABRIC);
    }

    // ----------------------------- Static Platform Checks -----------------------------

    /**
     * Check if the platform is Bukkit based
     *
     * @param platform The platform
     * @return True if the platform is Bukkit based, false otherwise
     */
    static boolean isBukkitBased(Platform platform) {
        return platform == Platforms.BUKKIT
                || platform == Platforms.SPIGOT
                || platform == Platforms.PAPER
                || platform == Platforms.PURPUR
                || platform == Platforms.PUFFERFISH
                || platform == Platforms.FOLIA
                || platform == Platforms.POSEIDON
                || isHybrid(platform);
    }

    /**
     * Check if the platform is BungeeCord based
     *
     * @param platform The platform
     * @return True if the platform is BungeeCord based, false otherwise
     */
    static boolean isBungeeCordBased(Platform platform) {
        return platform == Platforms.BUNGEECORD
                || platform == Platforms.WATERFALL
                || platform == Platforms.LIGHTFALL
                || platform == Platforms.TRAVERTINE;
    }

    /**
     * Check if the platform is Fabric based
     *
     * @param platform The platform
     * @return True if the platform is Fabric based, false otherwise
     */
    static boolean isFabricBased(Platform platform) {
        return platform == Platforms.FABRIC
                || platform == Platforms.QUILT
                || isFabricHybrid(platform);
    }

    /**
     * Check if the platform is Forge based
     *
     * @param platform The platform
     * @return True if the platform is Forge based, false otherwise
     */
    static boolean isFabricHybrid(Platform platform) {
        return platform == Platforms.CARDBOARD || platform == Platforms.BANNER;
    }

    /**
     * Check if the platform is Forge based
     *
     * @param platform The platform
     * @return True if the platform is Forge based, false otherwise
     */
    static boolean isForgeBased(Platform platform) {
        return platform == Platforms.FORGE
                || platform == Platforms.GOLDENFORGE
                || isForgeHybrid(platform);
    }

    /**
     * Check if the platform is Forge based
     *
     * @param platform The platform
     * @return True if the platform is Forge based, false otherwise
     */
    static boolean isForgeHybrid(Platform platform) {
        return platform == Platforms.MCPCPLUSPLUS
                || platform == Platforms.CAULDRON
                || platform == Platforms.KCAULDRON
                || platform == Platforms.THERMOS
                || platform == Platforms.CRUCIBLE
                || platform == Platforms.MOHIST
                || platform == Platforms.MAGMA
                || platform == Platforms.KETTING;
    }

    /**
     * Check if the platform is NeoForge based
     *
     * @param platform The platform
     * @return True if the platform is NeoForge based, false otherwise
     */
    static boolean isNeoForgeBased(Platform platform) {
        return platform == Platforms.NEOFORGE || isNeoForgeHybrid(platform);
    }

    /**
     * Check if the platform is NeoForge based
     *
     * @param platform The platform
     * @return True if the platform is NeoForge based, false otherwise
     */
    static boolean isNeoForgeHybrid(Platform platform) {
        return platform == Platforms.YOUER;
    }

    /**
     * Check if the platform is a hybrid
     *
     * @param platform The platform
     * @return True if the platform is a hybrid, false otherwise
     */
    static boolean isHybrid(Platform platform) {
        return platform == Platforms.ARCLIGHT
                || isForgeHybrid(platform)
                || isFabricHybrid(platform);
    }

    // ----------------------------- Misc -----------------------------

    /**
     * Check if Brigadier is supported
     *
     * @return True if Brigadier is supported, false otherwise
     */
    default boolean isBrigadierSupported() {
        return this.version().isAtLeast(MinecraftVersions.V13)
                || this.isPlatformPresent(Platforms.VELOCITY)
                || ReflectionUtil.checkForClass("com.mojang.brigadier.CommandDispatcher");
    }

    // ----------------------------- Exceptions -----------------------------

    /** Exception for when there's no platform found */
    final class NoPlatformException extends IllegalStateException {
        public NoPlatformException() {
            super(
                    "No platform found, this really shouldn't happen, unless the platform you're using hasn't been implemented in the API");
        }
    }

    /** Exception for when someone tries to redefine the primary platform */
    final class RedefinePrimaryPlatformException extends IllegalStateException {
        public RedefinePrimaryPlatformException() {
            super(
                    "The primary platform has already been set, if it's being set again, it usually indicates a platform-init-flow related issue");
        }
    }

    /** Exception for when there's no primary platform specified */
    final class NoPrimaryPlatformException extends IllegalStateException {
        public NoPrimaryPlatformException() {
            super(
                    "No primary platform found, please call MetaAPIImpl#setPrimaryPlatform(Platform) to set it");
        }
    }

    /** Exception for when there's no platform metadata detected for the specified platform */
    final class NoPlatformMetaException extends IllegalStateException {
        public NoPlatformMetaException(Platform platform) {
            super(
                    "No metadata found for platform "
                            + platform.name()
                            + ". This shouldn't normally happen, please file a bug report");
        }
    }
}
