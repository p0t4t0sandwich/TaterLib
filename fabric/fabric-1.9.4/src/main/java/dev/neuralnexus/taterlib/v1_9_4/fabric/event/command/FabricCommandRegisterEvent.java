/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_9_4.fabric.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9_4.fabric.command.FabricCommandWrapper;

import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandManager;

/** Fabric implementation of {@link CommandRegisterEvent}. */
public class FabricCommandRegisterEvent implements CommandRegisterEvent {
    private final CommandManager manager;
    private final boolean dedicated;

    public FabricCommandRegisterEvent(CommandManager manager, boolean dedicated) {
        this.manager = manager;
        this.dedicated = dedicated;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(Command command, String... aliases) {
        manager.register(new FabricCommandWrapper(command), aliases);
    }
}
