package dev.neuralnexus.taterlib.modules.core;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.GenericEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.hooks.metrics.SparkHook;
import dev.neuralnexus.taterlib.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterlib.modules.core.command.TaterLibCommand;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterlib.storage.databases.Database;
import dev.neuralnexus.taterlib.storage.datastores.player.PlayerDataStore;

/** TaterLib's core module. */
public class CoreModule implements PluginModule {
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "Core";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!TaterLib.hasReloaded()) {
            // Dump basic debug info
            new DumpInfo().saveDump();

            // Setup Generic Events
            GenericEvents.setup();

            // Setup local player metadata store
            TaterAPIProvider.setPlayerDataStore(
                    new PlayerDataStore(
                            new Database.DatabaseConfig(
                                    TaterLib.Constants.PROJECT_NAME, 0, "playerdata", "", "")));

            // Register hooks
            ServerEvents.STARTED.register(
                    event -> {
                        // Register LuckPerms hook
                        if (TaterAPIProvider.isPluginModLoaded("LuckPerms")
                                && TaterLibConfigLoader.config().checkHook("LuckPerms")) {
                            TaterLib.logger().info("LuckPerms detected, enabling LuckPerms hook.");
                            TaterAPIProvider.addHook(new LuckPermsHook());
                        }
                        // Register Spark hook
                        if (TaterAPIProvider.isPluginModLoaded("Spark")
                                && TaterLibConfigLoader.config().checkHook("Spark")) {
                            TaterLib.logger().info("Spark detected, enabling Spark hook.");
                            TaterAPIProvider.addHook(new SparkHook());
                        }
                    });

            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        Command command = new TaterLibCommand();
                        if (TaterAPIProvider.serverType().isBungeeCordBased()) {
                            command.setName("b" + command.name());
                        } else if (TaterAPIProvider.serverType().isVelocityBased()) {
                            command.setName("v" + command.name());
                        }
                        event.registerCommand(TaterLib.plugin(), command);
                    });
        }
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;
    }
}
