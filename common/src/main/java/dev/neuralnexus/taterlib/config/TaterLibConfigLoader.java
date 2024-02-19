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
import java.util.Objects;
import java.util.Set;

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
    private static TaterLibConfig config;

    /** Copy the default configuration to the config folder. */
    public static void copyDefaults() {
        if (configPath.toFile().exists()) {
            return;
        }
        try {
            Files.createDirectories(configPath.getParent());
            Files.copy(
                    Objects.requireNonNull(
                            TaterLibConfigLoader.class
                                    .getClassLoader()
                                    .getResourceAsStream(defaultConfigPath)),
                    configPath);
        } catch (IOException e) {
            TaterLib.logger()
                    .error(
                            "An error occurred while copying the default configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /** Load the configuration from the file. */
    public static void load() {
        copyDefaults();

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root;
        try {
            root = loader.load();
        } catch (ConfigurateException e) {
            TaterLib.logger()
                    .error("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);

        TypeToken<ServerConfig> serverType = new TypeToken<ServerConfig>() {};
        ConfigurationNode serverNode = root.node("server");
        ServerConfig server = null;
        try {
            server = serverNode.get(serverType);
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error(
                            "An error occurred while loading the server configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        TypeToken<Set<ModuleConfig>> moduleType = new TypeToken<Set<ModuleConfig>>() {};
        ConfigurationNode moduleNode = root.node("modules");
        Set<ModuleConfig> modules = null;
        try {
            modules = moduleNode.get(moduleType);
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error(
                            "An error occurred while loading the modules configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        TypeToken<Set<HookConfig>> hookType = new TypeToken<Set<HookConfig>>() {};
        ConfigurationNode hookNode = root.node("hooks");
        Set<HookConfig> hooks = null;
        try {
            hooks = hookNode.get(hookType);
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error(
                            "An error occurred while loading the hooks configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        TypeToken<Set<MixinConfig>> type = new TypeToken<Set<MixinConfig>>() {};
        ConfigurationNode mixinNode = root.node("mixins");
        Set<MixinConfig> mixins = null;
        try {
            mixins = mixinNode.get(type);
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error(
                            "An error occurred while loading the mixins configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

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
        CommentedConfigurationNode root;
        try {
            root = loader.load();
        } catch (ConfigurateException e) {
            TaterLib.logger()
                    .error("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return;
        }

        try {
            root.node("version").set(config.version());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("server").set(config.server());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("modules").set(config.modules());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("hooks").set(config.hooks());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("mixins").set(config.mixins());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

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
