/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterapi.impl.loader;

import dev.neuralnexus.taterapi.loader.EntrypointLoader;
import dev.neuralnexus.taterapi.loader.plugin.Plugin;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.ModContainer;
import dev.neuralnexus.taterapi.meta.ModResource;
import dev.neuralnexus.taterapi.meta.Platform;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;

public final class TaterLoader {
    public static final String MOD_ID = "taterlib";
    public static final String MOD_NAME = "TaterLib";
    public static final String VERSION = "2.0.0-SNAPSHOT";
    public static final String AUTHORS = "p0t4t0sandwich";
    public static final String DESCRIPTION =
            "A cross API code library that allows developers to write code that works across multiple modding platforms, and across a wide range of Minecraft versions, all with one JAR file. If TaterLib runs on it, so can your plugin/mod.";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterLib";

    public static final Logger logger = Logger.create(MOD_ID);

    private static final @NonNull String SERVICE_PATH =
            "META-INF/services/dev.neuralnexus.taterapi.loader.plugin.Plugin";
    private static EntrypointLoader<Plugin> loader;

    @ApiStatus.Internal
    public static void onInit() {
        if (CheckForBad.checkForTLauncher()) {
            throw new RuntimeException("TaterLib does not support TLauncher");
        }
        if (!MetaAPI.instance().isModLoaded("handsoffmydata") && CheckForBad.checkForBrightSDK()) {
            throw new RuntimeException(
                    "TaterLib does not support environments containing BrightSDK, please install HandsOffMyData to ensure that your data is safe.");
        }

        final MetaAPI api = MetaAPI.instance();
        final MinecraftVersion mcv = api.version();
        final Platform platform = api.platform();

        // spotless:off
        logger.info("Initializing " + MOD_NAME + " on "
                + "Minecraft " + mcv
                + " (" + platform + " " + api.meta().apiVersion() + ")");
        // spotless:on

        // final boolean debug = Constraint.Evaluator.DEBUG;
        // Constraint.Evaluator.DEBUG = this.debug().enabled();

        final ModContainer<?> container = MetaAPI.instance().mod(MOD_ID).orElseThrow();
        try (final ModResource resource = container.resource()) {
            final Path servicePath = resource.getResourceOrThrow(SERVICE_PATH);
            loader =
                    EntrypointLoader.builder()
                            .entrypointClass(Plugin.class)
                            .logger(logger)
                            .servicePaths(servicePath)
                            .useServiceLoader(false)
                            .useOtherProviders(true)
                            .build();

            loader.load();
        } catch (final Exception e) {
            logger.error("Failed to access " + MOD_NAME + " Mod Resources: " + e.getClass(), e);
        }
        loader.onInit();
    }

    @ApiStatus.Internal
    public static void onEnable() {
        loader.onEnable();
    }

    @ApiStatus.Internal
    public static void onDisable() {
        loader.onDisable();
    }
}
