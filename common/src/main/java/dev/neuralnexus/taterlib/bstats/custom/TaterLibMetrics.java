package dev.neuralnexus.taterlib.bstats.custom;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.Logger;

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
    private static final Logger LOGGER = TaterLib.logger();
    private static final int SERVICE_ID = 21198;

    public static void initialize() {
        MetricsConfig config;
        try {
            if (TaterAPIProvider.platform().isFabricBased()
                    || TaterAPIProvider.platform().isForgeBased()) {
                config = new MetricsConfig(new File("configs/bstats/config.txt"), true);
            } else {
                config = new MetricsConfig(new File("plugins/bStats/config.txt"), true);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to create bStats config!", e);
            return;
        }

        if (!config.didExistBefore()) {
            LOGGER.info(
                    "TaterLib is collecting metrics and sending them to bStats (https://bstats.org).");
            LOGGER.info(
                    "bStats collects some basic information for plugin authors, like how many people use their plugin and their total player count.");
            LOGGER.info(
                    "It's recommended to keep bStats enabled, but if you're not comfortable with this, you can opt-out by editing the config.txt file in");
            LOGGER.info(
                    "the '/plugins/bStats/' (or '/configs/bstats/' on modded servers) folder and setting enabled to false.");
        }

        MetricsBase metrics =
                new MetricsBase(
                        "other",
                        config.getServerUUID(),
                        SERVICE_ID,
                        config.isEnabled(),
                        TaterLibMetrics::appendPlatformData,
                        (appendPlatformData) -> {},
                        null,
                        () -> true,
                        LOGGER::warn,
                        LOGGER::info,
                        config.isLogErrorsEnabled(),
                        config.isLogSentDataEnabled(),
                        config.isLogResponseStatusTextEnabled());

        // TODO: Abstract this into SimpleServer
        String onlineMode = "true";
        try {
            File serverPropertiesFile = new File("server.properties");
            String serverProperties = new String(Files.readAllBytes(serverPropertiesFile.toPath()));
            if (!serverProperties.contains("online-mode=true")) {
                onlineMode = "false";
            }
        } catch (IOException ignored) {
        }
        String finalOnlineMode = onlineMode;
        //

        metrics.addCustomChart(
                new SingleLineChart(
                        "players",
                        () -> TaterAPIProvider.get().server().onlinePlayers().size()));
        metrics.addCustomChart(new SimplePie("online_mode", () -> finalOnlineMode));
        metrics.addCustomChart(
                new SimplePie("taterlib_version", () -> TaterLib.Constants.PROJECT_VERSION));
        metrics.addCustomChart(
                new SimplePie(
                        "modloader_version", () -> TaterAPIProvider.get().modLoaderVersion()));
        metrics.addCustomChart(
                new SimplePie(
                        "minecraft_version", () -> TaterAPIProvider.minecraftVersion().toString()));
        metrics.addCustomChart(
                new SimplePie("server_type", () -> TaterAPIProvider.platform().toString()));
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

    private static void appendPlatformData(JsonObjectBuilder builder) {
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }
}
