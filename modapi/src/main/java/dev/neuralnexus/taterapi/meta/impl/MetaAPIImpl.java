/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta.impl;

import static dev.neuralnexus.taterapi.meta.impl.util.ReflectionUtil.checkForClass;

import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.logger.impl.SystemLogger;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterapi.meta.Platform;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterapi.meta.Side;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.BungeeCordMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.FabricMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.NeoForgeMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.VanillaMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.VelocityMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.bukkit.BukkitMeta;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.forge.ForgeData;
import dev.neuralnexus.taterapi.meta.impl.platform.meta.sponge.SpongeData;
import dev.neuralnexus.taterapi.reflecto.MappingEntry;
import dev.neuralnexus.taterapi.reflecto.Reflecto;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Class implementing the metadata cache and other useful shortcuts. */
public final class MetaAPIImpl implements MetaAPI {
    private static MetaAPIImpl INSTANCE;

    public static MetaAPIImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MetaAPIImpl();
        }
        return INSTANCE;
    }

    private static Mappings mappings;

    static Reflecto.MappingStore store;

    private MetaAPIImpl() {}

    private void initReflection() {
        // Don't reflect on proxies or in server-only environments
        if (!Platforms.get().isEmpty() // Avoids errors in unit tests
                && !this.isProxy()
                && !this.isPlatformPresent(Platforms.BUKKIT)) {
            store = Reflecto.instance().getStore(this);

            if (this.isClient()) {
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
                                .mcp(
                                        "getMinecraft",
                                        MinecraftVersions.UNKNOWN,
                                        MinecraftVersions.V12_2)
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
            }

            Logger logger = Logger.create("MetaAPI");
            logger.info("Registered Minecraft reflection mappings");
            logger.info("|-> getInstance");
            logger.info("|-> hasServer");
            logger.info("|-> getServer");

            var mcString = "net.minecraft.server.MinecraftServer";
            var mcServer =
                    MappingEntry.builder("MinecraftServer")
                            .official(mcString)
                            .mojang(mcString)
                            .spigot(mcString)
                            .legacySpigot(mcString)
                            .searge(mcString)
                            .searge(mcString)
                            .legacySearge(mcString)
                            .mcp(mcString)
                            .yarnIntermediary(mcString)
                            .legacyIntermediary(mcString);

            var mcServer_isDedicatedServer =
                    MappingEntry.builder("isDedicatedServer")
                            .parentEntry(mcServer)
                            .mojang("isDedicatedServer")
                            .searge("m_6982_")
                            .legacySearge("func_71262_S")
                            .mcp("isDedicatedServer")
                            .yarnIntermediary("method_3816")
                            .legacyIntermediary("method_2983");

            store.registerClass(mcServer).registerMethod(mcServer_isDedicatedServer);

            logger.info("Registered MinecraftServer reflection mappings");
            logger.info("|-> isDedicatedServer");
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
        if (store == null) {
            initReflection();
        }
        return lookupAll().stream()
                .map(Platform.Meta::server)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Object client() {
        if (store == null) {
            initReflection();
        }
        return lookupAll().stream()
                .map(Platform.Meta::client)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Object minecraft() {
        if (store == null) {
            initReflection();
        }
        return lookupAll().stream()
                .map(Platform.Meta::minecraft)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    @Override
    public @NotNull Side side() {
        if (store == null) {
            initReflection();
        }
        return lookupAll().stream()
                .map(Platform.Meta::side)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean isClient() {
        return lookupAll().stream().anyMatch(Platform.Meta::isClient);
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
                .map(MetaAPIImpl::lookup)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
