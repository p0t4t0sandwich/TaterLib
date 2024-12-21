/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import dev.neuralnexus.modapi.metadata.impl.MetaAPIImpl;

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
     * Get the metadata for the primary platform
     *
     * @return The Platform's metadata
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     * @throws NoPlatformMetaException if there's no metadata for the platform
     */
    Platform.Meta meta() throws NoPrimaryPlatformException, NoPlatformMetaException;

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
    boolean isLoaded(@NotNull String nameOrId) throws NullPointerException;

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
    Optional<Boolean> isLoaded(@NotNull Platform platform, @NotNull String nameOrId)
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
