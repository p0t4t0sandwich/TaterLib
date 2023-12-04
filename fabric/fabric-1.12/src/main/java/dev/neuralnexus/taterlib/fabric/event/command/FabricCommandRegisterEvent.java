package dev.neuralnexus.taterlib.fabric.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;

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
        final LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        dispatcher.register(literalArgumentBuilder);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(literalArgumentBuilder.build()));
        }
    }
}
