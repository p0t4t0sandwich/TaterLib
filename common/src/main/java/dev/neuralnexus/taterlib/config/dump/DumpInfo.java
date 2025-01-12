/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.taterapi.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.TaterLib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

/** General dump information for debugging. */
public class DumpInfo {
    public final String platform = MetaAPI.instance().primaryPlatform().asString();
    public final String version = MetaAPI.instance().version().asString();
    public final String taterlibVersion = LoaderImpl.PROJECT_VERSION;
    public final boolean isForgeHybrid = MetaAPI.instance().isForgeHybrid();
    public final boolean isFabricHybrid = MetaAPI.instance().isFabricHybrid();
    public final boolean isSpongeForge = MetaAPI.instance().isSpongeForge();
    public final boolean isMixedForgeFabric = MetaAPI.instance().isMixedForgeFabric();

    /** Save the dump to a file. */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void saveDump() {
        Path logs = Paths.get("logs");
        if (!logs.toFile().exists()) {
            logs.toFile().mkdir();
        }
        try (Writer writer = new FileWriter("logs" + File.separator + "taterlib-dump.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            TaterLib.logger().error("An error occurred while saving the dump.", e);
        }
    }
}
