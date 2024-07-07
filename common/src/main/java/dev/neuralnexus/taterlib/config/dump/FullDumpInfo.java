/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterapi.metadata.ModInfo;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.modules.mclogs.api.MCLogsAPI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** Full dump information for debugging. */
public class FullDumpInfo extends DumpInfo {
    public String latestLog;
    public String debugLog;
    public String latestCrashLog;
    public List<ModInfo> bukkitPlugins;
    public List<ModInfo> bungeePlugins;
    public List<ModInfo> spongePlugins;
    public List<ModInfo> velocityPlugins;
    public List<ModInfo> forgeMods;
    public List<ModInfo> neoForgeMods;
    public List<ModInfo> fabricMods;

    public FullDumpInfo() {
        if (platform.isBukkitBased()) {
            this.bukkitPlugins = TaterAPIProvider.api(Platform.BUKKIT).get().modList();
        }
        if (platform.isBungeeCordBased()) {
            this.bungeePlugins = TaterAPIProvider.api(Platform.BUNGEECORD).get().modList();
        }
        if (platform.isSpongeBased()) {
            this.spongePlugins = TaterAPIProvider.api(Platform.SPONGE).get().modList();
        }
        if (platform.isVelocityBased()) {
            this.velocityPlugins = TaterAPIProvider.api(Platform.VELOCITY).get().modList();
        }
        if (Platform.isNeoForge()) {
            this.neoForgeMods = TaterAPIProvider.api(Platform.NEOFORGE).get().modList();
        }
        if (Platform.isForge()) {
            this.forgeMods = TaterAPIProvider.api(Platform.FORGE).get().modList();
        }
        if (Platform.isFabric()) {
            this.fabricMods = TaterAPIProvider.api(Platform.FABRIC).get().modList();
        }
    }

    /** Save the dump to a file. */
    @Override
    public void saveDump() {
        MCLogsAPI.uploadLatestLog().ifPresent(upload -> latestLog = upload.getUrl());
        MCLogsAPI.uploadLatestDebugLog().ifPresent(upload -> debugLog = upload.getUrl());
        MCLogsAPI.uploadLatestCrashReport().ifPresent(upload -> latestCrashLog = upload.getUrl());

        Path logs = Paths.get("logs");
        if (!logs.toFile().exists()) {
            logs.toFile().mkdir();
        }
        try (Writer writer = new FileWriter("logs" + File.separator + "taterlib-fulldump.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            TaterLib.logger().error("An error occurred while saving the dump.");
            e.printStackTrace();
        }
    }
}
