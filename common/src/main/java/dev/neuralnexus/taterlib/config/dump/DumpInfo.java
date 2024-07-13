/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterloader.impl.LoaderImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

/** General dump information for debugging. */
public class DumpInfo {
    public final Platform platform = Platform.get();
    public final MinecraftVersion minecraftVersion = MinecraftVersion.get();
    public final String taterlibVersion = LoaderImpl.PROJECT_VERSION;
    public final boolean isForgeHybrid = platform.isForgeHybrid();
    public final boolean isFabricHybrid = platform.isFabricHybrid();
    public final boolean isSpongeForge = platform.is(Platform.SPONGE_FORGE);
    public final boolean isSinytraConnector =
            TaterAPIProvider.api().get().isModLoaded("connectormod");
    public final boolean isKilt = TaterAPIProvider.api().get().isModLoaded("kilt");

    /** Save the dump to a file. */
    public void saveDump() {
        Path logs = Paths.get("logs");
        if (!logs.toFile().exists()) {
            logs.toFile().mkdir();
        }
        try (Writer writer = new FileWriter("logs" + File.separator + "taterlib-dump.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            TaterLib.logger().error("An error occurred while saving the dump.");
            e.printStackTrace();
        }
    }
}
