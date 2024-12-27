/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.modapi.metadata.impl;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.Mappings;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersion;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.modapi.metadata.Platform;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.impl.logger.SystemLogger;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.BungeeCordMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.FabricMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.NeoForgeMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VanillaMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.VelocityMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.bukkit.BukkitMeta;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.forge.ForgeData;
import dev.neuralnexus.modapi.metadata.impl.platform.meta.sponge.SpongeData;
import dev.neuralnexus.modapi.metadata.impl.util.ReflectionUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Class implementing the metadata cache and other useful shortcuts. */
public final class MetaAPIImpl implements MetaAPI {
    private static final MetaAPIImpl INSTANCE = new MetaAPIImpl();

    public static MetaAPIImpl getInstance() {
        return INSTANCE;
    }

    private MetaAPIImpl() {}

    // ----------------------------- Platform -----------------------------

    private Platform primaryPlatform;

    @Override
    public Platform primaryPlatform() throws NoPrimaryPlatformException {
        if (this.primaryPlatform == null) {
            throw new NoPrimaryPlatformException();
        }
        return this.primaryPlatform;
    }

    @Override
    public void setPrimaryPlatform(@NotNull Platform platform)
            throws RedefinePrimaryPlatformException, NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        if (this.primaryPlatform != null) {
            throw new RedefinePrimaryPlatformException();
        }
        this.primaryPlatform = platform;
    }

    @Override
    public boolean isPrimaryPlatform(@NotNull Platform platform)
            throws NoPrimaryPlatformException, NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        return this.primaryPlatform() == platform;
    }

    @Override
    public Platform platform() throws NoPlatformException {
        if (this.primaryPlatform == null) {
            return Platforms.get().stream().findFirst().orElseThrow(NoPlatformException::new);
        }
        return this.primaryPlatform;
    }

    @Override
    public boolean isPlatformPresent(@NotNull Platform platform) throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        return Platforms.get().contains(platform);
    }

    @Override
    public Platform.Meta meta() throws NoPrimaryPlatformException, NoPlatformMetaException {
        return lookup(this.primaryPlatform())
                .orElseThrow(() -> new NoPlatformMetaException(this.primaryPlatform()));
    }

    @Override
    public Optional<Platform.Meta> meta(@NotNull Platform platform) throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        return lookup(platform);
    }

    // ----------------------------- Platform.Meta Getters -----------------------------

    @Override
    public MinecraftVersion version() {
        return lookupAll().stream()
                .map(Platform.Meta::minecraftVersion)
                .findFirst()
                .orElse(MinecraftVersions.UNKNOWN);
    }

    @Override
    public boolean isModLoaded(@NotNull String nameOrId) throws NullPointerException {
        Objects.requireNonNull(nameOrId, "Name or ID cannot be null");
        return lookupAll().stream().anyMatch(meta -> meta.isModLoaded(nameOrId));
    }

    @Override
    public Optional<Boolean> isModLoaded(@NotNull Platform platform, @NotNull String nameOrId)
            throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        Objects.requireNonNull(nameOrId, "Name or ID cannot be null");
        return lookup(platform).map(meta -> meta.isModLoaded(nameOrId));
    }

    @Override
    public Mappings mappings() {
        return lookupAll().stream()
                .map(Platform.Meta::mappings)
                .findFirst()
                .orElse(Mappings.UNKNOWN);
    }

    @Override
    public Optional<Mappings> mappings(@NotNull Platform platform) throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        return lookup(platform).map(Platform.Meta::mappings);
    }

    @Override
    public Logger logger(@NotNull String modId) throws NullPointerException {
        Objects.requireNonNull(modId, "Mod ID cannot be null");
        return lookupAll().stream()
                .map(meta -> meta.logger(modId))
                .findFirst()
                .orElse(new SystemLogger(modId));
    }

    /**
     * Get the metadata for the specified platform
     *
     * @param platform The Platform
     * @return The Platform's metadata
     */
    public static Optional<Platform.Meta> lookup(Platform platform) {
        if (MetaAPI.isNeoForgeBased(platform)) {
            return Optional.of(new NeoForgeMeta());
        } else if (MetaAPI.isForgeBased(platform)) {
            return Optional.ofNullable(ForgeData.create());
        } else if (MetaAPI.isFabricBased(platform)) {
            return Optional.of(new FabricMeta());
        } else if (platform == Platforms.SPONGE) {
            return Optional.ofNullable(SpongeData.create());
        } else if (MetaAPI.isBukkitBased(platform)) {
            return Optional.of(new BukkitMeta());
        } else if (MetaAPI.isBungeeCordBased(platform)) {
            return Optional.of(new BungeeCordMeta());
        } else if (platform == Platforms.VELOCITY) {
            return Optional.of(new VelocityMeta());
        } else if (ReflectionUtil.checkForClass("org.spongepowered.asm.service.MixinService")) {
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
                .map(MetaAPIImpl::lookup)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
