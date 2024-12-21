/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata;

import java.util.Optional;

/** Class holding the metadata cache and other useful shortcuts. */
public final class MetaAPI {
    public static final MetaAPI INSTANCE = new MetaAPI();

    public static MetaAPI instance() {
        return INSTANCE;
    }

    private MetaAPI() {}

    // ----------------------------- Platform -----------------------------

    private Platform primaryPlatform;

    /**
     * Set the primary platform that the environment is running
     *
     * @param platform The platform
     * @throws RedefinePrimaryPlatformException if the primary platform is already defined
     */
    public void setPrimaryPlatform(Platform platform) throws RedefinePrimaryPlatformException {
        if (this.primaryPlatform != null) {
            throw new RedefinePrimaryPlatformException();
        }
        this.primaryPlatform = platform;
    }

    /**
     * Get the primary platform that the environment is running
     *
     * @return The platform
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     */
    public Platform primaryPlatform() throws NoPrimaryPlatformException {
        if (this.primaryPlatform == null) {
            throw new NoPrimaryPlatformException();
        }
        return this.primaryPlatform;
    }

    /**
     * Check if a platform is the same as the one identified as the primary platform
     *
     * @param platform The platform to check
     * @return True, if they match, false otherwise
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     */
    public boolean isPrimaryPlatform(Platform platform) throws NoPrimaryPlatformException {
        return this.primaryPlatform() == platform;
    }

    /**
     * Get the platform the environment is running, returns the primary platform, or the first
     * platform in the list of detected platforms. Essentially a more lenient alternative to {@link
     * MetaAPI#primaryPlatform()}
     *
     * @return The platform
     * @throws NoPlatformException if there is no platform detected
     */
    public Platform platform() throws NoPlatformException {
        if (this.primaryPlatform == null) {
            return Platforms.get().stream().findFirst().orElseThrow(NoPlatformException::new);
        }
        return this.primaryPlatform;
    }

    /**
     * Get the metadata for the primary platform
     *
     * @return The Platform's metadata
     * @throws NoPrimaryPlatformException if the primary platform is not detected
     * @throws NoPlatformMetaException if there's no metadata for the platform
     */
    public Platform.Meta meta() throws NoPrimaryPlatformException, NoPlatformMetaException {
        return Platforms.Meta.lookup(this.primaryPlatform())
                .orElseThrow(() -> new NoPlatformMetaException(this.primaryPlatform()));
    }

    /**
     * Get the metadata for the specified platform
     *
     * @param platform The Platform
     * @return The Platform's metadata
     */
    public Optional<Platform.Meta> meta(Platform platform) {
        return Platforms.Meta.lookup(platform);
    }

    // ----------------------------- Platform.Meta Getters -----------------------------

    /**
     * Get the version of Minecraft the server is running
     *
     * @return The current Minecraft version
     */
    public MinecraftVersion version() {
        return Platforms.Meta.lookupAll().stream()
                .map(Platform.Meta::minecraftVersion)
                .findFirst()
                .orElse(MinecraftVersion.UNKNOWN);
    }

    /**
     * Get if a mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started
     *
     * @param nameOrId The name of the plugin or modId of the mod
     * @return True if the mod is loaded, false otherwise
     */
    public boolean isLoaded(String nameOrId) {
        return Platforms.Meta.lookupAll().stream().anyMatch(meta -> meta.isLoaded(nameOrId));
    }

    /**
     * Get if a mod is loaded <br>
     * Note: Unless you need to check at a specific time, it's best to run this check after the
     * server has started
     *
     * @param platform The platform
     * @param nameOrId The name of the plugin or modId of the mod
     * @return True if the mod is loaded, false otherwise
     */
    public Optional<Boolean> isLoaded(Platform platform, String nameOrId) {
        return Platforms.Meta.lookup(platform).map(meta -> meta.isLoaded(nameOrId));
    }

    /**
     * Get the platform's mappings
     *
     * @return The platform's mappings
     */
    public Mappings mappings() {
        return Platforms.Meta.lookupAll().stream()
                .map(Platform.Meta::mappings)
                .findFirst()
                .orElse(Mappings.UNKNOWN);
    }

    /**
     * Get the platform's mappings
     *
     * @param platform The platform
     * @return The platform's mappings
     */
    public Optional<Mappings> mappings(Platform platform) {
        return Platforms.Meta.lookup(platform).map(Platform.Meta::mappings);
    }

    /**
     * Get a new logger for the specified modId
     *
     * @param modId The mod id
     * @return A new Logger
     * @throws NoPlatformMetaException if there's no metadata for the platform
     */
    public Logger logger(String modId) throws NoPlatformMetaException {
        return Platforms.Meta.lookupAll().stream()
                .map(meta -> meta.logger(modId))
                .findFirst()
                .orElseThrow(NoPlatformMetaException::new);
    }

    // ----------------------------- Exceptions -----------------------------

    /** Exception for when there's no platform found */
    public static final class NoPlatformException extends IllegalStateException {
        NoPlatformException() {
            super(
                    "No platform found, this really shouldn't happen, unless the platform you're using hasn't been implemented in the API");
        }
    }

    /** Exception for when someone tries to redefine the primary platform */
    public static final class RedefinePrimaryPlatformException extends IllegalStateException {
        RedefinePrimaryPlatformException() {
            super(
                    "The primary platform has already been set, if it's being set again, it usually indicates a platform-init-flow related issue");
        }
    }

    /** Exception for when there's no primary platform specified */
    public static final class NoPrimaryPlatformException extends IllegalStateException {
        NoPrimaryPlatformException() {
            super(
                    "No primary platform found, please call MetaAPI#setPrimaryPlatform(Platform) to set it");
        }
    }

    /** Exception for when there's no platform metadata detected for the specified platform */
    public static final class NoPlatformMetaException extends IllegalStateException {
        NoPlatformMetaException(Platform platform) {
            super(
                    "No metadata found for platform "
                            + platform.name()
                            + ". This shouldn't normally happen, please file a bug report");
        }

        NoPlatformMetaException() {
            super(
                    "No metadata found for the platform. This shouldn't normally happen, please file a bug report");
        }
    }
}
