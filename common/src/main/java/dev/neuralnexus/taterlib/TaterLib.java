/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib;

import dev.neuralnexus.modapi.crossperms.CrossPerms;
import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.impl.loader.TaterLoader;
import dev.neuralnexus.taterapi.impl.loader.plugin.ModuleLoaderImpl;
import dev.neuralnexus.taterapi.loader.plugin.ModuleLoader;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterlib.config.TaterLibConfig;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.metrics.bstats.TaterLibMetrics;
import dev.neuralnexus.taterlib.modules.bungeecord.BungeeCordModule;
import dev.neuralnexus.taterlib.modules.core.CoreModule;
import dev.neuralnexus.taterlib.modules.mclogs.MCLogsModule;

import org.jspecify.annotations.NonNull;

/** Main class for the plugin. */
public class TaterLib {
    private static boolean RELOADED = false;
    private static final ModuleLoader moduleLoader = new ModuleLoaderImpl();

    private TaterLib() {}

    /**
     * Get if the plugin has reloaded
     *
     * @return If the plugin has reloaded
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean hasReloaded() {
        return RELOADED;
    }

    public static @NonNull Object mod() {
        return MetaAPI.instance().mod(TaterLoader.MOD_ID).orElseThrow().unwrap();
    }

    // TODO: Create shared flag to signify that events have been registered
    // Alternatively, include the "source" platform as part of the event call to allow for filtering

    /** Start */
    public static void start() {
        MetaAPI api = MetaAPI.instance();
        TaterAPI.logger()
                .info(
                        TaterLoader.MOD_NAME
                                + " is running on "
                                + api.platform()
                                + " "
                                + api.version()
                                + ", with "
                                + api.mappings()
                                + " mappings!");

        //        try {
        //            String[] repos = new String[] {"https://maven.neuralnexus.dev/mirror"};
        //            Map<String, String> deps = new HashMap<>();
        //            deps.put("gs.mclo:api:3.0.1", "a0f52bb4002f4fe958e9c7af8d2e34fb");
        //            DepClassLoader depClassLoader =
        //                    new DepClassLoader(TaterLib.class.getClassLoader(), repos, deps);
        //            depClassLoader.downloadAll();
        //            depClassLoader.addDepsToClasspath();
        //        } catch (Exception e) {
        //            logger().error("Could not download dependencies", e);
        //        }

        TaterLibMetrics.initialize();
        TaterLibConfigLoader.load();

        if (!RELOADED) {
            ServerEvents.STARTING.register(event -> CrossPerms.instance().onInit());
            ServerEvents.STARTED.register(event -> CrossPerms.instance().onEnable());
            ServerEvents.STOPPED.register(
                    event -> {
                        TaterLibMetrics.shutdown();
                        TaterAPI.scheduler().shutdownBackgroundScheduler();
                    });

            TaterLibConfig config = TaterLibConfigLoader.config();
            moduleLoader.registerModule(new CoreModule());
            if (config.checkModule("BungeeCord")) {
                moduleLoader.registerModule(new BungeeCordModule());
            }
            if (config.checkModule("MCLogs")) {
                moduleLoader.registerModule(new MCLogsModule());
            }
        }
        TaterAPI.logger().info(TaterLoader.MOD_NAME + " has been started!");
        moduleLoader.onEnable();
    }

    /** Stop */
    public static void stop() {
        moduleLoader.onDisable();
        TaterLibConfigLoader.unload();
        TaterAPI.logger().info(TaterLoader.MOD_NAME + " has been stopped!");
    }

    /** Reload */
    public static void reload() {
        RELOADED = true;
        stop();
        start();
        TaterAPI.logger().info(TaterLoader.MOD_NAME + " has been reloaded!");
    }
}
