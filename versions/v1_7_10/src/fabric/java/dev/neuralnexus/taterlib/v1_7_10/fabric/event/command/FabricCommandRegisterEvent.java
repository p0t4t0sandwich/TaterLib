/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.event.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;

import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandManager;

/** Fabric implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class FabricCommandRegisterEvent implements CommandRegisterEvent {
    private final CommandManager manager;

    public FabricCommandRegisterEvent(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void registerCommand(Command command, String... aliases) {
        //        manager.register(new FabricCommandWrapper(command), aliases);
    }
}
