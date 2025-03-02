/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.mclogs.api;

import dev.neuralnexus.taterapi.impl.loader.LoaderImpl;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterlib.TaterLib;

import gs.mclo.api.MclogsClient;
import gs.mclo.api.Util;
import gs.mclo.api.response.InsightsResponse;
import gs.mclo.api.response.UploadLogResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/** API for the MCLogs module. */
public class MCLogsAPI {
    private static final MclogsClient client =
            new MclogsClient("TaterLib-MCLogsModule/" + LoaderImpl.PROJECT_VERSION)
                    .setProjectName("TaterLib-MCLogsModule")
                    .setProjectVersion(LoaderImpl.PROJECT_VERSION)
                    .setMinecraftVersion(MetaAPI.instance().version().toString());

    /**
     * Uploads a log to MCLogs.
     *
     * @param log The log to upload.
     * @return The URL of the log.
     */
    public static Optional<UploadLogResponse> uploadLog(String log) {
        try {
            return Optional.ofNullable(client.uploadLog(Paths.get(log)));
        } catch (IOException e) {
            TaterLib.logger().info("Failed to upload log.");
            return Optional.empty();
        }
    }

    /**
     * Uploads a log to MCLogs.
     *
     * @param log The log to upload.
     * @return The URL of the log.
     */
    public static Optional<UploadLogResponse> uploadLogString(String log) {
        try {
            InputStream in = Files.newInputStream(Paths.get(log));
            String content = Util.inputStreamToString(in);
            return Optional.ofNullable(client.uploadLog(content));
        } catch (IOException e) {
            TaterLib.logger().info("Failed to upload log.");
            return Optional.empty();
        }
    }

    /**
     * Get raw log data from MCLogs.
     *
     * @param id The ID of the log.
     * @return The raw log data.
     */
    public static Optional<String> getRawLogContent(String id) {
        try {
            return Optional.ofNullable(client.getRawLogContent(id));
        } catch (IOException e) {
            TaterLib.logger().info("Failed to get raw log content.");
            return Optional.empty();
        }
    }

    /**
     * Get the insights of a log from MCLogs.
     *
     * @param id The ID of the log.
     * @return The insights of the log.
     */
    public static Optional<InsightsResponse> getLogInsights(String id) {
        try {
            return Optional.ofNullable(client.getInsights(id));
        } catch (IOException e) {
            TaterLib.logger().info("Failed to get log insights.");
            return Optional.empty();
        }
    }

    /**
     * Upload the latest.log file to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    public static Optional<UploadLogResponse> uploadLatestLog() {
        return uploadLog("logs" + File.separator + "latest.log");
    }

    /**
     * Upload debug.log file to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    public static Optional<UploadLogResponse> uploadLatestDebugLog() {
        return uploadLog("logs" + File.separator + "debug.log");
    }

    /**
     * Upload latest crash report to MCLogs.
     *
     * @return The UploadLogResponse of the upload.
     */
    @SuppressWarnings("resource")
    public static Optional<UploadLogResponse> uploadLatestCrashReport() {
        try {
            Path dir = Paths.get("." + File.separator + "crash-reports");
            Optional<Path> lastFilePath =
                    Files.list(dir)
                            .filter(f -> !Files.isDirectory(f))
                            .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
            if (lastFilePath.isPresent()) {
                return uploadLog(lastFilePath.get().toString());
            }
        } catch (IOException e) {
            TaterLib.logger().info("No crash reports found.");
        }
        return Optional.empty();
    }

    /**
     * List all logs in the ./logs directory.
     *
     * @return A list of all logs in the ./logs directory.
     */
    public static List<String> listLogs() {
        return Arrays.stream(client.listLogsInDirectory("." + File.separator))
                .collect(Collectors.toList());
    }

    /**
     * List all crash reports in the ./crash-reports directory.
     *
     * @return A list of all crash reports in the ./crash-reports directory.
     */
    public static List<String> listCrashReports() {
        return Arrays.stream(client.listCrashReportsInDirectory("." + File.separator))
                .collect(Collectors.toList());
    }
}
