package dev.neuralnexus.taterloader;

import dev.neuralnexus.taterapi.loader.EntrypointLoader;
import dev.neuralnexus.taterapi.loader.plugin.NewPlugin;
import dev.neuralnexus.taterapi.logger.Logger;
import dev.neuralnexus.taterapi.meta.Constraint;
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
    //public static final String MOD_VERSION = "2.0.0-SNAPSHOT";

    public static final Logger logger = Logger.create(MOD_ID);

    private static final @NonNull String SERVICE_PATH =
            "META-INF/services/dev.neuralnexus.taterapi.loader.plugin.NewPlugin";
    private static EntrypointLoader<NewPlugin> loader;

    @ApiStatus.Internal
    public static void onInit() {
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
                            .entrypointClass(NewPlugin.class)
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
