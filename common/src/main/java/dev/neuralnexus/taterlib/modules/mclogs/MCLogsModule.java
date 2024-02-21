package dev.neuralnexus.taterlib.modules.mclogs;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.modules.mclogs.command.MCLogsCommand;
import dev.neuralnexus.taterlib.plugin.PluginModule;

/** MCLogs module. */
public class MCLogsModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "MCLogs";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterLib.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        Command command = new MCLogsCommand();
                        if (TaterAPIProvider.serverType().isBungeeCordBased()) {
                            command.setName("bmclogs");
                        } else if (TaterAPIProvider.serverType().isVelocityBased()) {
                            command.setName("vmclogs");
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
