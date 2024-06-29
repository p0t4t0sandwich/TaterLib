/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

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

        if (!TaterLib.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        Command command = new MCLogsCommand();
                        if (TaterAPIProvider.platform().isBungeeCordBased()) {
                            command.setName("bmclogs");
                        } else if (TaterAPIProvider.platform().isVelocityBased()) {
                            command.setName("vmclogs");
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
