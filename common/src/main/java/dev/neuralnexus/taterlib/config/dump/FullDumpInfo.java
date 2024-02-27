package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ModInfo;
import dev.neuralnexus.taterlib.api.info.PluginInfo;
import dev.neuralnexus.taterlib.api.info.ServerType;
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
        if (serverType.isBukkitBased()) {
            this.bukkitPlugins = TaterAPIProvider.get(ServerType.BUKKIT).pluginList();
        }
        if (serverType.isBungeeCordBased()) {
            this.bungeePlugins = TaterAPIProvider.get(ServerType.BUNGEECORD).pluginList();
        }
        if (serverType.isSpongeBased()) {
            this.spongePlugins = TaterAPIProvider.get(ServerType.SPONGE).pluginList();
        }
        if (serverType.isVelocityBased()) {
            this.velocityPlugins = TaterAPIProvider.get(ServerType.VELOCITY).pluginList();
        }
        if (ServerType.isNeoForge()) {
            this.neoForgeMods = TaterAPIProvider.get(ServerType.NEOFORGE).modList();
        }
        if (ServerType.isForge()) {
            this.forgeMods = TaterAPIProvider.get(ServerType.FORGE).modList();
        }
        if (ServerType.isFabric()) {
            this.fabricMods = TaterAPIProvider.get(ServerType.FABRIC).modList();
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
