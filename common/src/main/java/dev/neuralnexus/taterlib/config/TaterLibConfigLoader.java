package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;
import dev.neuralnexus.taterlib.config.versions.TaterLibConfig_V1;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/** A class for loading TaterLib configuration. */
public class TaterLibConfigLoader {
    private static final Path configPath =
            Paths.get(
                    TaterAPIProvider.serverType().dataFolders().configFolder()
                            + File.separator
                            + TaterLib.Constants.PROJECT_NAME
                            + File.separator
                            + TaterLib.Constants.PROJECT_ID
                            + ".conf");
    private static final String defaultConfigPath =
            "source." + TaterLib.Constants.PROJECT_ID + ".conf";
    private static final TypeToken<Integer> versionType = new TypeToken<Integer>() {};
    private static final TypeToken<ServerConfig> serverType = new TypeToken<ServerConfig>() {};
    private static final TypeToken<List<ModuleConfig>> moduleType =
            new TypeToken<List<ModuleConfig>>() {};
    private static final TypeToken<List<HookConfig>> hookType =
            new TypeToken<List<HookConfig>>() {};
    private static final TypeToken<List<MixinConfig>> mixinType =
            new TypeToken<List<MixinConfig>>() {};
    private static TaterLibConfig config;

    /** Load the configuration from the file. */
    public static void load() {
        ConfigUtils.copyDefaults(TaterLib.class, configPath, defaultConfigPath, TaterLib.logger());

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = ConfigUtils.getRoot(loader);
        if (root == null) {
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);
        ServerConfig server = ConfigUtils.get(root, serverType, "server", TaterLib.logger());
        List<ModuleConfig> modules =
                ConfigUtils.get(root, moduleType, "modules", TaterLib.logger());
        List<HookConfig> hooks = ConfigUtils.get(root, hookType, "hooks", TaterLib.logger());
        List<MixinConfig> mixins = ConfigUtils.get(root, mixinType, "mixins", TaterLib.logger());

        switch (version) {
            case 1:
                config = new TaterLibConfig_V1(version, server, modules, hooks, mixins);
                break;
            default:
                System.err.println("Unknown configuration version: " + version);
        }
    }

    /** Unload the configuration. */
    public static void unload() {
        config = null;
    }

    /** Save the configuration to the file. */
    public static void save() {
        if (config == null) {
            return;
        }
        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = ConfigUtils.getRoot(loader);
        if (root == null) {
            return;
        }

        ConfigUtils.set(root, versionType, "version", config.version(), TaterLib.logger());
        ConfigUtils.set(root, serverType, "server", config.server(), TaterLib.logger());
        ConfigUtils.set(root, moduleType, "modules", config.modules(), TaterLib.logger());
        ConfigUtils.set(root, hookType, "hooks", config.hooks(), TaterLib.logger());
        ConfigUtils.set(root, mixinType, "mixins", config.mixins(), TaterLib.logger());

        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /**
     * Get the loaded configuration.
     *
     * @return The loaded configuration.
     */
    public static TaterLibConfig config() {
        if (config == null) {
            load();
        }
        return config;
    }
}
