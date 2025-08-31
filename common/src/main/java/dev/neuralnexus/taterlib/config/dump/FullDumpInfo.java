/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.ModInfo;
import dev.neuralnexus.taterapi.meta.Platforms;
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
    public List<ModInfo> bukkit;
    public List<ModInfo> bungeecord;
    public List<ModInfo> fabric;
    public List<ModInfo> forge;
    public List<ModInfo> neoforge;
    public List<ModInfo> sponge;
    public List<ModInfo> velocity;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public FullDumpInfo() {
        MetaAPI api = MetaAPI.instance();
        if (api.isPlatformPresent(Platforms.BUKKIT)) {
            this.bukkit = MetaAPI.instance().meta(Platforms.BUKKIT).get().mods();
        }
        if (api.isPlatformPresent(Platforms.BUNGEECORD)) {
            this.bungeecord = MetaAPI.instance().meta(Platforms.BUNGEECORD).get().mods();
        }
        if (api.isPlatformPresent(Platforms.FABRIC)) {
            this.fabric = MetaAPI.instance().meta(Platforms.FABRIC).get().mods();
        }
        if (api.isPlatformPresent(Platforms.FORGE)) {
            this.forge = MetaAPI.instance().meta(Platforms.FORGE).get().mods();
        }
        if (api.isPlatformPresent(Platforms.NEOFORGE)) {
            this.neoforge = MetaAPI.instance().meta(Platforms.NEOFORGE).get().mods();
        }
        if (api.isPlatformPresent(Platforms.SPONGE)) {
            this.sponge = MetaAPI.instance().meta(Platforms.SPONGE).get().mods();
        }
        if (api.isPlatformPresent(Platforms.VELOCITY)) {
            this.velocity = MetaAPI.instance().meta(Platforms.VELOCITY).get().mods();
        }
    }

    /** Save the dump to a file. */
    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
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
            TaterAPI.logger().error("An error occurred while saving the dump.", e);
        }
    }
}
