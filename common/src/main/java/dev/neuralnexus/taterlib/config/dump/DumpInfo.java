package dev.neuralnexus.taterlib.config.dump;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

/** General dump information for debugging. */
public class DumpInfo {
    public final ServerType serverType = ServerType.serverType();
    public final MinecraftVersion minecraftVersion = MinecraftVersion.minecraftVersion();
    public final String taterlibVersion = TaterLib.Constants.PROJECT_VERSION;
    public final boolean isForgeHybrid = serverType.isForgeHybrid();
    public final boolean isFabricHybrid = serverType.isFabricHybrid();
    public final boolean isSpongeForge = serverType.is(ServerType.SPONGE_FORGE);
    public final boolean isSinytraConnector =
            serverType.isForgeBased() && TaterAPIProvider.get().isModLoaded("connectormod");

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
