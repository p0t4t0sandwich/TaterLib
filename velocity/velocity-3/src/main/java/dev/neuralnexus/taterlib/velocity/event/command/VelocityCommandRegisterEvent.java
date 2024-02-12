package dev.neuralnexus.taterlib.velocity.event.command;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.velocity.command.VelocityCommandWrapper;

/** Velocity implementation of {@link CommandRegisterEvent}. */
public class VelocityCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        CommandManager commandManager = ((ProxyServer) plugin).getCommandManager();
        CommandMeta commandMeta =
                commandManager.metaBuilder(command.name()).aliases(aliases).plugin(plugin).build();
        commandManager.register(commandMeta, new VelocityCommandWrapper(command::execute));
    }
}
