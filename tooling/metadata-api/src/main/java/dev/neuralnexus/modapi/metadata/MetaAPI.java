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

    private Platform primaryPlatform;

    /**
     * Set the primary platform that the environment is running
     *
     * @param platform The platform
     * @throws RedefinePrimaryPlatformException if the primary platform is already defined
     */
    public void setPrimaryPlatform(Platform platform) {
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
    public Platform primaryPlatform() {
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
    public boolean isPrimaryPlatform(Platform platform) {
        return this.primaryPlatform() == platform;
    }

    /**
     * Get the platform the environment is running, returns the primary platform, or the first
     * platform in the list of detected platforms. Essentially a more lenient alternative to {@link
     * MetaAPI#primaryPlatform()}
     *
     * @return The platform
     * @throws NoPlatformException if there is no platform can be detected
     */
    public Platform platform() {
        if (this.primaryPlatform == null) {
            return Platforms.get().stream().findFirst().orElseThrow(NoPlatformException::new);
        }
        return this.primaryPlatform;
    }

    /**
     * Get the metadata for the primary platform
     * @return The Platform's metadata
     */
    public Platform.Meta meta() {
        if (this.primaryPlatform == null) {
            throw new NoPrimaryPlatformException();
        }
        return Platforms.Meta.lookup(this.primaryPlatform)
                .orElseThrow(() -> new NoPlatformMetaException(this.primaryPlatform));
    }

    public Optional<Platform.Meta> meta(Platform platform) {
        return Platforms.Meta.lookup(platform);
    }

    /** Get a new logger for the specified pluginId. */
    public Logger<?> logger(String pluginId) {
        Platform platform;
        if (this.primaryPlatform == null) {
            platform = Platforms.get().stream().findFirst().orElseThrow(NoPlatformException::new);
        } else {
            platform = this.primaryPlatform;
        }
        return this.meta(platform)
                .orElseThrow(() -> new NoPlatformMetaException(platform))
                .logger(pluginId);
    }

    /** Get the asString of Minecraft the server is running. */
    public MinecraftVersion version() {
        return this.meta().minecraftVersion();
    }

    public static final class NoPlatformException extends IllegalStateException {
        NoPlatformException() {
            super(
                    "No platform found, this really shouldn't happen, unless the platform you're using hasn't been implemented in the API");
        }
    }

    public static final class RedefinePrimaryPlatformException extends IllegalStateException {
        RedefinePrimaryPlatformException() {
            super(
                    "The primary platform has already been set, if it's being set again, it usually indicates a platform-init-flow related issue");
        }
    }

    public static final class NoPrimaryPlatformException extends IllegalStateException {
        NoPrimaryPlatformException() {
            super(
                    "No primary platform found, please call MetaAPI#setPrimaryPlatform(Platform) to set it");
        }
    }

    public static final class NoPlatformMetaException extends IllegalStateException {
        NoPlatformMetaException(Platform platform) {
            super(
                    "No metadata found for platform "
                            + platform.name()
                            + ". This shouldn't normally happen, please file a bug report");
        }
    }
}
