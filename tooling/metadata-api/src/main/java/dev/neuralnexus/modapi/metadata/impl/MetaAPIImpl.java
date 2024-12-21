/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl;

import static dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.BungeeCordMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.FabricMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.NeoForgeMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VanillaMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VelocityMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.bukkit.BukkitMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.forge.ForgeData;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.sponge.SpongeData;

import java.util.List;
import java.util.Optional;

/** Class holding the metadata cache and other useful shortcuts. */
public final class MetaAPIImpl implements MetaAPI {
    private static final MetaAPIImpl INSTANCE = new MetaAPIImpl();

    public static MetaAPIImpl getInstance() {
        return INSTANCE;
    }

    private MetaAPIImpl() {}

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
     * MetaAPIImpl#primaryPlatform()}
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
        return Meta.lookup(this.primaryPlatform())
                .orElseThrow(() -> new NoPlatformMetaException(this.primaryPlatform()));
    }

    /**
     * Get the metadata for the specified platform
     *
     * @param platform The Platform
     * @return The Platform's metadata
     */
    public Optional<Platform.Meta> meta(Platform platform) {
        return Meta.lookup(platform);
    }

    // ----------------------------- Platform.Meta Getters -----------------------------

    /**
     * Get the version of Minecraft the server is running
     *
     * @return The current Minecraft version
     */
    public MinecraftVersion version() {
        return Meta.lookupAll().stream()
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
        return Meta.lookupAll().stream().anyMatch(meta -> meta.isLoaded(nameOrId));
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
        return Meta.lookup(platform).map(meta -> meta.isLoaded(nameOrId));
    }

    /**
     * Get the platform's mappings
     *
     * @return The platform's mappings
     */
    public Mappings mappings() {
        return Meta.lookupAll().stream()
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
        return Meta.lookup(platform).map(Platform.Meta::mappings);
    }

    /**
     * Get a new logger for the specified modId
     *
     * @param modId The mod id
     * @return A new Logger
     * @throws NoPlatformMetaException if there's no metadata for the platform
     */
    public Logger logger(String modId) throws NoPlatformMetaException {
        return Meta.lookupAll().stream()
                .map(meta -> meta.logger(modId))
                .findFirst()
                .orElseThrow(NoPlatformMetaException::new);
    }

    private static final class Meta {
        /**
         * Get the metadata for the specified platform
         *
         * @param platform The Platform
         * @return The Platform's metadata
         */
        public static Optional<Platform.Meta> lookup(Platform platform) {
            if (platform.isNeoForge()) {
                return Optional.of(new NeoForgeMeta());
            } else if (platform.isForge()) {
                return Optional.ofNullable(ForgeData.create());
            } else if (platform.isFabric()) {
                return Optional.of(new FabricMeta());
            } else if (platform.isSponge()) {
                return Optional.ofNullable(SpongeData.create());
            } else if (platform.isBukkit()) {
                return Optional.of(new BukkitMeta());
            } else if (platform.isBungeeCord()) {
                return Optional.of(new BungeeCordMeta());
            } else if (platform.isVelocity()) {
                return Optional.of(new VelocityMeta());
            } else if (checkForClass("org.spongepowered.asm.service.MixinService")) {
                return Optional.of(new VanillaMeta());
            }
            return Optional.empty();
        }

        /**
         * Get the metadata for the primary platform
         *
         * @return The Platform's metadata
         */
        public static List<Platform.Meta> lookupAll() {
            return Platforms.get().stream()
                    .map(Meta::lookup)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }
    }
}
