/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.mclogs;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.loader.plugin.PluginModule;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.modules.mclogs.command.MCLogsCommand;

/** MCLogs module. */
public class MCLogsModule implements PluginModule {
    @Override
    public String id() {
        return "MCLogs";
    }

    @Override
    public void onEnable() {
        if (!TaterLib.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        Command command = new MCLogsCommand();
                        if (MetaAPI.instance().isPlatformPresent(Platforms.BUNGEECORD)) {
                            command.setName("bmclogs");
                        } else if (MetaAPI.instance().isPlatformPresent(Platforms.VELOCITY)) {
                            command.setName("vmclogs");
                        }
                        event.registerCommand(command);
                    });
        }
    }
}
