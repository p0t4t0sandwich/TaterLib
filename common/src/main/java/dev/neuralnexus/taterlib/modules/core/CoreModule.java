package dev.neuralnexus.taterlib.modules.core;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.config.ConfigLoader;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.GenericEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.hooks.TaterLibHook;
import dev.neuralnexus.taterlib.hooks.permissions.LuckPermsHook;
import dev.neuralnexus.taterlib.modules.core.command.TaterLibCommand;
import dev.neuralnexus.taterlib.plugin.PluginModule;

/** TaterLib's core module. */
public class CoreModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

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

        if (!RELOADED) {
            // Setup Generic Events
            GenericEvents.setup();

            TaterAPI api = TaterAPIProvider.get();

            // Register TaterLib hook (in case other plugins use hooks to check for TaterLib)
            TaterAPIProvider.addHook(new TaterLibHook());

            // Register hooks
            ServerEvents.STARTED.register(
                    event -> {
                        // Register LuckPerms hook
                        if (api.isPluginModLoaded("LuckPerms")
                                && ConfigLoader.config().checkHook("LuckPerms")) {
                            TaterLib.logger().info("LuckPerms detected, enabling LuckPerms hook.");
                            TaterAPIProvider.addHook(new LuckPermsHook());
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

        TaterLib.logger().info("Submodule " + name() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects

        TaterLib.logger().info("Submodule " + name() + " has been stopped!");
    }

    @Override
    public void reload() {
        if (!STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        TaterLib.logger().info("Submodule " + name() + " has been reloaded!");
    }
}
