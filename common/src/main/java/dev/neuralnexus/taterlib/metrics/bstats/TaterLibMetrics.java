/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.metrics.bstats;

import dev.neuralnexus.modapi.metadata.Logger;
import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.modapi.metadata.Side;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import org.bstats.MetricsBase;
import org.bstats.charts.DrilldownPie;
import org.bstats.charts.SimplePie;
import org.bstats.charts.SingleLineChart;
import org.bstats.config.MetricsConfig;
import org.bstats.json.JsonObjectBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;

/** TaterLib's custom bStats implementation. */
public class TaterLibMetrics {
    private static final Logger logger = TaterLib.logger();
    private static final int SERVICE_ID = 21198;
    private static MetricsBase metrics;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void initialize() {
        if (TaterAPIProvider.side().is(Side.CLIENT)) {
            return;
        }
        MetricsConfig config;
        try {
            boolean defaultEnabled = TaterAPIProvider.side().is(Side.SERVER);
            if (MetaAPI.instance()
                    .isPlatformPresent(
                            Platforms.FABRIC,
                            Platforms.FORGE,
                            Platforms.NEOFORGE,
                            Platforms.SPONGE)) {
                config = new MetricsConfig(new File("config/bstats/config.txt"), defaultEnabled);
            } else {
                config = new MetricsConfig(new File("plugins/bStats/config.txt"), defaultEnabled);
            }
        } catch (IOException e) {
            logger.error("Failed to create bStats config!", e);
            return;
        }

        if (!config.didExistBefore()) {
            logger.info(
                    "TaterLib is collecting metrics and sending them to bStats (https://bstats.org).");
            logger.info(
                    "bStats collects some basic information for plugin authors, like how many people use their plugin and their total player count.");
            logger.info(
                    "It's recommended to keep bStats enabled, but if you're not comfortable with this, you can opt-out by editing the config.txt file in");
            logger.info(
                    "the '/plugins/bStats/' (or '/config/bstats/' on modded servers) folder and setting enabled to false.");
        }

        metrics =
                new MetricsBase(
                        "other",
                        config.getServerUUID(),
                        SERVICE_ID,
                        config.isEnabled(),
                        TaterLibMetrics::appendPlatformData,
                        (appendPlatformData) -> {},
                        null,
                        () -> true,
                        logger::warn,
                        logger::info,
                        config.isLogErrorsEnabled(),
                        config.isLogSentDataEnabled(),
                        config.isLogResponseStatusTextEnabled());

        // TODO: Abstract this into SimpleServer
        String onlineMode = "true";
        try {
            File serverPropertiesFile = new File("server.properties");
            String serverProperties = new String(Files.readAllBytes(serverPropertiesFile.toPath()));
            if (serverProperties.contains("online-mode=false")) {
                onlineMode = "false";
            }
        } catch (IOException ignored) {
        }
        String finalOnlineMode = onlineMode;
        //

        metrics.addCustomChart(
                new SingleLineChart(
                        "players",
                        () -> TaterAPIProvider.api().get().server().onlinePlayers().size()));
        metrics.addCustomChart(new SimplePie("online_mode", () -> finalOnlineMode));
        metrics.addCustomChart(new SimplePie("taterlib_version", () -> LoaderImpl.PROJECT_VERSION));
        metrics.addCustomChart(
                new SimplePie(
                        "modloader_version", () -> MetaAPI.instance().meta().loaderVersion()));
        metrics.addCustomChart(
                new SimplePie("minecraft_version", () -> MetaAPI.instance().version().asString()));
        metrics.addCustomChart(new SimplePie("platform", () -> MetaAPI.instance().toString()));
        metrics.addCustomChart(
                new DrilldownPie(
                        "java_version",
                        () -> {
                            String javaVersion = System.getProperty("java.version");
                            String major = javaVersion.split("\\.")[0];
                            int dot = javaVersion.lastIndexOf('.');
                            String version;
                            if (major.equals("1")) {
                                version = javaVersion.substring(0, dot);
                            } else if (major.matches("\\d+")) {
                                version = major;
                            } else {
                                version = major;
                            }
                            return Collections.singletonMap(
                                    "Java " + version, Collections.singletonMap(javaVersion, 1));
                        }));
    }

    public static void shutdown() {
        metrics.shutdown();
    }

    private static void appendPlatformData(JsonObjectBuilder builder) {
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }
}
