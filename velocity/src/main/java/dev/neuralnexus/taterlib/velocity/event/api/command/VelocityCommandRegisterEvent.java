package dev.neuralnexus.taterlib.velocity.event.api.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;
import dev.neuralnexus.taterlib.velocity.command.VelocityCommandWrapper;

/**
 * Velocity implementation of {@link CommandRegisterEvent}.
 */
public class VelocityCommandRegisterEvent implements CommandRegisterEvent {
    /**
     * @inheritDoc
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        VelocityTaterLibPlugin.getProxyServer().getCommandManager().register(command.getName(), new VelocityCommandWrapper(command::execute));
    }
}
