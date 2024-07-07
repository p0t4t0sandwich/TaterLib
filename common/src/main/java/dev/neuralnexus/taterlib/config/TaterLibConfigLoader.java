/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.config;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.sections.HookConfig;
import dev.neuralnexus.taterlib.config.sections.MixinConfig;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterlib.config.sections.ServerConfig;
import dev.neuralnexus.taterlib.config.versions.TaterLibConfig_V1;
import dev.neuralnexus.taterlib.loader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.logger.Logger;
import dev.neuralnexus.taterlib.logger.impl.SystemLogger;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** A class for loading TaterLib configuration. */
public class TaterLibConfigLoader {
    private static final Logger logger = new SystemLogger("TaterLibConfigLoader");
    private static final Path configPath =
            Paths.get(
                    TaterAPIProvider.platformData().configFolder()
                            + File.separator
                            + LoaderImpl.PROJECT_ID
                            + File.separator
                            + LoaderImpl.PROJECT_ID
                            + ".conf");
    private static final String defaultConfigPath = "source." + LoaderImpl.PROJECT_ID + ".conf";
    private static final TypeToken<Integer> versionType = new TypeToken<Integer>() {};
    private static final TypeToken<ServerConfig> serverType = new TypeToken<ServerConfig>() {};
    private static final TypeToken<List<ModuleConfig>> moduleType =
            new TypeToken<List<ModuleConfig>>() {};
    private static final TypeToken<List<HookConfig>> hookType =
            new TypeToken<List<HookConfig>>() {};
    private static final TypeToken<MixinConfig> mixinType = new TypeToken<MixinConfig>() {};
    private static TaterLibConfig config;

    /** Load the configuration from the file. */
    public static void load() {
        ConfigUtil.copyDefaults(TaterLibConfigLoader.class, configPath, defaultConfigPath, logger);

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = ConfigUtil.getRoot(loader, logger);
        if (root == null) {
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);
        ServerConfig server = ConfigUtil.get(root, serverType, "server", logger);
        List<ModuleConfig> modules = ConfigUtil.get(root, moduleType, "modules", logger);
        List<HookConfig> hooks = ConfigUtil.get(root, hookType, "hooks", logger);
        MixinConfig mixin = ConfigUtil.get(root, mixinType, "mixin", logger);

        switch (version) {
            case 1:
                config = new TaterLibConfig_V1(version, server, modules, hooks, mixin);
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
        CommentedConfigurationNode root = ConfigUtil.getRoot(loader, logger);
        if (root == null) {
            return;
        }

        ConfigUtil.set(root, versionType, "version", config.version(), logger);
        ConfigUtil.set(root, serverType, "server", config.server(), logger);
        ConfigUtil.set(root, moduleType, "modules", config.modules(), logger);
        ConfigUtil.set(root, hookType, "hooks", config.hooks(), logger);
        ConfigUtil.set(root, mixinType, "mixin", config.mixin(), logger);

        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            logger.error("An error occurred while saving this configuration: " + e.getMessage());
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
