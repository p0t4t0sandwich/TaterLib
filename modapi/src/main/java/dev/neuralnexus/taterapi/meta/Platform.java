/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.meta;

import static dev.neuralnexus.taterapi.meta.impl.util.PathUtils.getConfigFolder;
import static dev.neuralnexus.taterapi.meta.impl.util.PathUtils.getModsFolder;

import dev.neuralnexus.taterapi.logger.Logger;

import net.minecraft.server.MinecraftServer;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public interface Platform {
    /**
     * Get the name of the platform.
     *
     * @return The name of the platform
     */
    String name();

    /**
     * Detect if the platform is available. <br>
     * Note: This is all handled internally, and you should not need to call this method.
     *
     * @return True if the platform is available, false otherwise
     */
    default boolean detect() {
        return detect(false);
    }

    /**
     * Detect if the platform is available. <br>
     * Note: This is all handled internally, and you should not need to call this method.
     *
     * @param force If true, the platform will be detected again, even if it was already detected
     * @return True if the platform is available, false otherwise
     */
    @ApiStatus.Internal
    boolean detect(boolean force);

    default boolean isBukkit() {
        return this.equals(Platforms.BUKKIT) || this.isSpigot() || this.isPaper();
    }

    default boolean isSpigot() {
        return this.equals(Platforms.SPIGOT);
    }

    default boolean isPaper() {
        return this.equals(Platforms.PAPER);
    }

    default boolean isBungeeCord() {
        return this.equals(Platforms.BUNGEECORD) || this.isWaterfall();
    }

    default boolean isWaterfall() {
        return this.equals(Platforms.WATERFALL);
    }

    default boolean isFabric() {
        return this.equals(Platforms.FABRIC) || this.isQuilt() || this.isFabricHybrid();
    }

    default boolean isQuilt() {
        return this.equals(Platforms.QUILT);
    }

    default boolean isFabricHybrid() {
        return this.equals(Platforms.CARDBOARD) || this.equals(Platforms.BANNER);
    }

    default boolean isForge() {
        return this.equals(Platforms.FORGE)
                || this.equals(Platforms.GOLDENFORGE)
                || this.isForgeHybrid();
    }

    default boolean isForgeHybrid() {
        return this.equals(Platforms.MCPCPLUSPLUS)
                || this.equals(Platforms.CAULDRON)
                || this.equals(Platforms.KCAULDRON)
                || this.equals(Platforms.THERMOS)
                || this.equals(Platforms.CRUCIBLE)
                || this.equals(Platforms.MAGMA)
                || this.equals(Platforms.KETTING);
    }

    default boolean isNeoForge() {
        return this.equals(Platforms.NEOFORGE);
    }

    default boolean isHybrid() {
        return this.equals(Platforms.MOHIST)
                || this.equals(Platforms.ARCLIGHT)
                || isForgeHybrid()
                || isFabricHybrid();
    }

    default boolean isSponge() {
        return this.equals(Platforms.SPONGE);
    }

    default boolean isVelocity() {
        return this.equals(Platforms.VELOCITY);
    }

    default boolean isProxy() {
        return isBungeeCord() || isVelocity();
    }

    /** The platform's metadata */
    interface Meta {
        /**
         * Get an instance of the platform's server. <br>
         * In abstracted environments, this will return the platform's server object. <br>
         * In client-only environments, this will return the client instance.
         *
         * @return The server instance
         */
        @NotNull Object server();

        /**
         * Get the client instance. Not available in server-only environments. <br>
         * It's safe to cast to {@link net.minecraft.client.Minecraft}. <br>
         * The only reason it isn't returned, is because the class is remapped in some environments
         * (notably Fabric).
         *
         * @return The client instance
         */
        @NotNull Object client();

        /**
         * Get an instance of the {@link MinecraftServer}. Not available on proxies or in
         * client-only environments.
         *
         * @return The MinecraftServer instance
         */
        @NotNull Object minecraft();

        /**
         * Get the platform's side
         *
         * @return The platform's side
         */
        @NotNull Side side();

        /**
         * Get the running Minecraft asString
         *
         * @return The running Minecraft asString
         */
        @NotNull MinecraftVersion minecraftVersion();

        /**
         * Get the modloader asString
         *
         * @return the modloader asString
         */
        @NotNull String loaderVersion();

        /**
         * Get the modloader API asString
         *
         * @return the modloader API asString
         */
        @NotNull String apiVersion();

        /**
         * Get the mod list
         *
         * @return The mod list
         */
        @NotNull List<ModInfo> modList();

        /**
         * Get the Logger
         *
         * @return The Logger
         */
        @NotNull Logger logger(@NotNull String pluginId);

        /**
         * Get if a mod is loaded <br>
         * Note: Unless you need to check at a specific time, it's best to run this check after the
         * server has started
         *
         * @param nameOrId The name of the plugin or modId of the mod
         * @return True if the mod is loaded, false otherwise
         */
        default boolean isModLoaded(@NotNull String nameOrId) {
            Objects.requireNonNull(nameOrId, "Mod name or ID cannot be null");
            return modList().stream()
                    .anyMatch(
                            modInfo ->
                                    modInfo.id().equalsIgnoreCase(nameOrId)
                                            || modInfo.name().equalsIgnoreCase(nameOrId));
        }

        /**
         * Get the path to the mod/plugin folder
         *
         * @return The path to the mod/plugin folder
         */
        default @NotNull Path modFolder() {
            return getModsFolder();
        }

        /**
         * Get the path to the config folder
         *
         * @return The path to the config folder
         */
        default @NotNull Path configFolder() {
            return getConfigFolder();
        }
    }
}
