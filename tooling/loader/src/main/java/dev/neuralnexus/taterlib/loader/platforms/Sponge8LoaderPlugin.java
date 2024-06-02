package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterLib.Constants.PROJECT_ID)
public class Sponge8LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge8LoaderPlugin(PluginContainer container, Logger logger) {
        loader = new TaterLibLoader(container, null, logger);
        loader.registerPlugin(plugin());
        if (TaterAPIProvider.serverType().is(ServerType.SPONGE_FORGE)) {
            loader.registerPlugin(ForgeLoaderPlugin.plugin());
        }
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin plugin() {
        String version;
        MinecraftVersion mcv = MinecraftVersion.minecraftVersion();
        if (mcv.isInRange(MinecraftVersion.V1_13, MinecraftVersion.V1_16_5)) {
            version = "." + MinecraftVersion.V1_13.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_17, MinecraftVersion.V1_18_2)) {
            version = "." + MinecraftVersion.V1_17.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_19, MinecraftVersion.V1_19_4)) {
            version = "." + MinecraftVersion.V1_19.getDelimiterString();
        } else if (mcv.isInRange(MinecraftVersion.V1_20, MinecraftVersion.V1_20_6)) {
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        } else {
            System.err.println(
                    "Unsupported Minecraft version: "
                            + mcv
                            + ". We'll try to load the latest version.");
            version = "." + MinecraftVersion.V1_20.getDelimiterString();
        }
        String pluginClassName =
                "dev.neuralnexus.taterlib" + version + ".sponge.SpongeTaterLibPlugin";
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            return (dev.neuralnexus.taterlib.plugin.Plugin)
                    pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load plugin class: " + pluginClassName);
            e.printStackTrace();
        }
        return null;
    }

    @Listener
    public void onServerStarting(ConstructPluginEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopping(StoppingEngineEvent<Server> event) {
        loader.onDisable();
    }
}
