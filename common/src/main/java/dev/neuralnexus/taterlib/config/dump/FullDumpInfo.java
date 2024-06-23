package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.Platform;
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
    public List<PluginInfo> bukkitPlugins;
    public List<PluginInfo> bungeePlugins;
    public List<PluginInfo> spongePlugins;
    public List<PluginInfo> velocityPlugins;
    public List<ModInfo> forgeMods;
    public List<ModInfo> neoForgeMods;
    public List<ModInfo> fabricMods;

    public FullDumpInfo() {
        if (platform.isBukkitBased()) {
            this.bukkitPlugins = TaterAPIProvider.get(Platform.BUKKIT).pluginList();
        }
        if (platform.isBungeeCordBased()) {
            this.bungeePlugins = TaterAPIProvider.get(Platform.BUNGEECORD).pluginList();
        }
        if (platform.isSpongeBased()) {
            this.spongePlugins = TaterAPIProvider.get(Platform.SPONGE).pluginList();
        }
        if (platform.isVelocityBased()) {
            this.velocityPlugins = TaterAPIProvider.get(Platform.VELOCITY).pluginList();
        }
        if (Platform.isNeoForge()) {
            this.neoForgeMods = TaterAPIProvider.get(Platform.NEOFORGE).modList();
        }
        if (Platform.isForge()) {
            this.forgeMods = TaterAPIProvider.get(Platform.FORGE).modList();
        }
        if (Platform.isFabric()) {
            this.fabricMods = TaterAPIProvider.get(Platform.FABRIC).modList();
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
