/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.core;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.config.TaterLibConfigLoader;
import dev.neuralnexus.taterlib.config.dump.DumpInfo;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.GenericEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.hooks.metrics.SparkHook;
import dev.neuralnexus.taterapi.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterloader.impl.LoaderImpl;
import dev.neuralnexus.taterlib.modules.core.command.TaterLibCommand;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterapi.storage.databases.Database;
import dev.neuralnexus.taterapi.storage.datastores.player.PlayerDataStore;

/** TaterLib's core module. */
public class CoreModule implements PluginModule {
    @Override
    public String id() {
        return "Core";
    }

    @Override
    public void onEnable() {
        if (!TaterLib.hasReloaded()) {
            // Dump basic debug info
            new DumpInfo().saveDump();

            // Setup Generic Events
            GenericEvents.setup();

            // Setup local player metadata store
            TaterAPIProvider.setPlayerDataStore(
                    new PlayerDataStore(
                            new Database.DatabaseConfig(
                                    LoaderImpl.PROJECT_ID, 0, "playerdata", "", "")));

            // Register hooks
            ServerEvents.STARTED.register(
                    event -> {
                        // Register LuckPerms hook
                        if (TaterAPIProvider.isModLoaded("LuckPerms")
                                && TaterLibConfigLoader.config().checkHook("LuckPerms")) {
                            TaterLib.logger().info("LuckPerms detected, enabling LuckPerms hook.");
                            TaterAPIProvider.addHook(new LuckPermsHook());
                        }
                        // Register Spark hook
                        if (TaterAPIProvider.isModLoaded("Spark")
                                && TaterLibConfigLoader.config().checkHook("Spark")) {
                            TaterLib.logger().info("Spark detected, enabling Spark hook.");
                            TaterAPIProvider.addHook(new SparkHook());
                        }
                    });

            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        Command command = new TaterLibCommand();
                        if (TaterAPIProvider.platform().isBungeeCordBased()) {
                            command.setName("b" + command.name());
                        } else if (TaterAPIProvider.platform().isVelocityBased()) {
                            command.setName("v" + command.name());
                        }
                        event.registerCommand(command);
                    });
        }
    }
}
