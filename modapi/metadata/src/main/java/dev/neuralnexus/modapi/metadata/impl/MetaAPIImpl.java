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
import dev.neuralnexus.modapi.metadata.Side;
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
import dev.neuralnexus.modapi.reflecto.MappingEntry;
import dev.neuralnexus.modapi.reflecto.Reflecto;

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

    private static Mappings mappings;

    static Reflecto.MappingStore store;

    private MetaAPIImpl() {
        // Don't reflect on proxies or in server-only environments
        if (!Platforms.get().isEmpty() // Avoids errors in unit tests
                && !this.isProxy()
                && !this.isPlatformPresent(Platforms.BUKKIT)) {
            store = Reflecto.instance().getStore(this);
            var minecraft =
                    MappingEntry.builder("Minecraft")
                            .official("net.minecraft.client.Minecraft")
                            .mojang("net.minecraft.client.Minecraft")
                            .searge("net.minecraft.client.Minecraft")
                            .legacySearge("net.minecraft.client.Minecraft")
                            .mcp("net.minecraft.client.Minecraft")
                            .yarnIntermediary("net.minecraft.class_310")
                            .legacyIntermediary("net.minecraft.class_1600");

            var minecraft_getInstance =
                    MappingEntry.builder("getInstance")
                            .parentEntry(minecraft)
                            .mojang("getInstance")
                            .searge("m_91087_")
                            .legacySearge("func_71410_x")
                            .mcp("getInstance")
                            .mcp("getMinecraft", MinecraftVersions.UNKNOWN, MinecraftVersions.V12_2)
                            .yarnIntermediary("method_1551")
                            .legacyIntermediary("method_2965");

            var minecraft_hasServer =
                    MappingEntry.builder("hasServer")
                            .parentEntry(minecraft)
                            .mojang("hasSingleplayerServer")
                            .searge("m_91091_")
                            .legacySearge("func_71356_B")
                            .mcp("isSingleplayer")
                            .yarnIntermediary("method_1496")
                            .legacyIntermediary("method_2908");

            var minecraft_getServer =
                    MappingEntry.builder("getServer")
                            .parentEntry(minecraft)
                            .official("getSinglePlayerServer")
                            .mojang("getSinglePlayerServer")
                            .searge("m_91092_")
                            .legacySearge("func_71401_C")
                            .mcp("getIntegratedServer")
                            .yarnIntermediary("method_1576")
                            .legacyIntermediary("method_2909");

            store.registerClass(minecraft)
                    .registerMethod(minecraft_getInstance)
                    .registerMethod(minecraft_hasServer)
                    .registerMethod(minecraft_getServer);

            Logger logger = new SystemLogger("MetaAPIImpl");
            logger.info("Registered Minecraft reflection mappings");
            logger.info("|-> getInstance");
            logger.info("|-> hasServer");
            logger.info("|-> getServer");
        }
    }

    // ----------------------------- Platform -----------------------------

    private Platform primaryPlatform;

    @Override
    public @NotNull Platform primaryPlatform() throws NoPrimaryPlatformException {
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
    public @NotNull Platform platform() throws NoPlatformException {
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
    public @NotNull Platform.Meta meta() throws NoPlatformException, NoPlatformMetaException {
        return lookup(this.platform())
                .orElseThrow(() -> new NoPlatformMetaException(this.platform()));
    }

    @Override
    public Optional<Platform.Meta> meta(@NotNull Platform platform) throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        return lookup(platform);
    }

    // ----------------------------- Platform.Meta Getters -----------------------------

    @Override
    public @NotNull Object server() {
        return lookupAll().stream()
                .map(Platform.Meta::server)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Object client() {
        return lookupAll().stream()
                .map(Platform.Meta::client)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Object minecraft() {
        return lookupAll().stream()
                .map(Platform.Meta::minecraft)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Side side() {
        return lookupAll().stream()
                .map(Platform.Meta::side)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public @NotNull MinecraftVersion version() {
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
    public boolean isModLoaded(@NotNull Platform platform, @NotNull String nameOrId)
            throws NullPointerException {
        Objects.requireNonNull(platform, "Platform cannot be null");
        Objects.requireNonNull(nameOrId, "Name or ID cannot be null");
        return lookup(platform).map(meta -> meta.isModLoaded(nameOrId)).orElse(false);
    }

    // TODO: At some point, it would be nice to have a guaranteed set of version-specific mappings
    // Would allow for more accurate mappings detection, rather than assumptions inflexible to
    // future changes
    @Override
    public @NotNull Mappings mappings() {
        if (mappings == null) {
            MetaAPI api = MetaAPI.instance();
            // Check for proxy
            if (api.isProxy()) {
                mappings = Mappings.NONE;
                // Check for connector and kilt
            } else if (api.isMixedForgeFabric() || api.isMixedNeoForgeFabric()) {
                if (api.isModLoaded(Platforms.FABRIC, "kilt")) {
                    mappings = Mappings.YARN_INTERMEDIARY;
                } else if (api.isModLoaded(Platforms.FORGE, "connector")) {
                    mappings = Mappings.SEARGE;
                } else if (api.isModLoaded(Platforms.NEOFORGE, "connector")) {
                    mappings = Mappings.MOJANG;
                }
                // Check NeoForge
            } else if (api.isPlatformPresent(Platforms.NEOFORGE)) {
                if (this.version().is(MinecraftVersions.V20_1)) {
                    mappings = Mappings.SEARGE;
                } else {
                    mappings = Mappings.MOJANG;
                }
                // Check Forge
            } else if (api.isPlatformPresent(Platforms.FORGE)) {
                if (this.version().isAtMost(MinecraftVersions.V16_5)) {
                    mappings = Mappings.LEGACY_SEARGE;
                } else if (this.version()
                        .isInRange(MinecraftVersions.V17, MinecraftVersions.V20_5)) {
                    mappings = Mappings.SEARGE;
                } else {
                    mappings = Mappings.MOJANG;
                }
                // Check Fabric
            } else if (api.isPlatformPresent(Platforms.FABRIC)) {
                // TODO: Add Babric and CursedFabric checks
                if (this.version().isOlderThan(MinecraftVersions.V14)) {
                    mappings = Mappings.LEGACY_INTERMEDIARY;
                } else {
                    mappings = Mappings.YARN_INTERMEDIARY;
                }
                // Check SpongeVanilla
            } else if (api.isPlatformPresent(Platforms.SPONGE)) {
                if (this.version().isOlderThan(MinecraftVersions.V14)) {
                    mappings = Mappings.SEARGE;
                } else {
                    mappings = Mappings.MOJANG;
                }
                // Check Paper
            } else if (api.isPlatformPresent(Platforms.PAPER)
                    && this.version().isAtLeast(MinecraftVersions.V20_5)) {
                mappings = Mappings.MOJANG;
                // Check Spigot
            } else if (api.isPlatformPresent(Platforms.SPIGOT)) {
                if (this.version().isOlderThan(MinecraftVersions.V18)) {
                    mappings = Mappings.LEGACY_SPIGOT;
                } else {
                    mappings = Mappings.SPIGOT;
                }
                // Check Bukkit
            } else if (api.isPlatformPresent(Platforms.BUKKIT)) {
                mappings = Mappings.OFFICIAL;
            } else {
                mappings = Mappings.OFFICIAL;
            }
        }
        return mappings;
    }

    @Override
    public @NotNull Logger logger(@NotNull String modId) throws NullPointerException {
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
