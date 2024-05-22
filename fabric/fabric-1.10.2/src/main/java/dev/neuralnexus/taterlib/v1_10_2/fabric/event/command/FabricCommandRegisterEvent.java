package dev.neuralnexus.taterlib.v1_10_2.fabric.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_10_2.fabric.command.FabricCommandWrapper;

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
    public void registerCommand(Object plugin, Command command, String... aliases) {
        manager.register(new FabricCommandWrapper(command), aliases);
    }
}
