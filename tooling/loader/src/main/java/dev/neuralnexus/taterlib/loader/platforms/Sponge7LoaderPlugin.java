package dev.neuralnexus.taterlib.loader.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.MinecraftVersion;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = TaterLib.Constants.PROJECT_ID,
        name = TaterLib.Constants.PROJECT_NAME,
        version = TaterLib.Constants.PROJECT_VERSION,
        description = TaterLib.Constants.PROJECT_DESCRIPTION)
public class Sponge7LoaderPlugin {
    private static Loader loader;

    @Inject
    public Sponge7LoaderPlugin(PluginContainer container, Logger logger) {
        loader = new TaterLibLoader(container, null, logger);
        loader.registerPlugin(getPlugin());
        if (TaterAPIProvider.serverType().is(ServerType.SPONGE_FORGE)) {
            loader.registerPlugin(ForgeLoaderPlugin.getPlugin());
        }
        loader.onInit();
    }

    public static dev.neuralnexus.taterlib.plugin.Plugin getPlugin() {
        String version = "";
        switch (MinecraftVersion.minecraftVersion()) {
                //            case V1_12:
                //            case V1_12_1:
                //            case V1_12_2:
                //                version = "." + MinecraftVersion.V1_12_2.getDelimiterString();
                //                break;
            default:
                System.err.println(
                        "Unsupported Minecraft version: " + MinecraftVersion.minecraftVersion());
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
    public void onServerStarted(GameStartedServerEvent event) {
        loader.onEnable();
    }

    @Listener
    public void onServerStopped(GameStoppedServerEvent event) {
        loader.onDisable();
    }
}
