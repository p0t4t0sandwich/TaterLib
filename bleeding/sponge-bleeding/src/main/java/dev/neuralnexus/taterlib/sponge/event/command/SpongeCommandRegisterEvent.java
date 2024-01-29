package dev.neuralnexus.taterlib.sponge.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.command.SpongeCommandWrapper;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.command.parameter.Parameter;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.plugin.PluginContainer;

/** Sponge implementation of {@link CommandRegisterEvent}. */
public class SpongeCommandRegisterEvent implements CommandRegisterEvent {
    private final RegisterCommandEvent<org.spongepowered.api.command.Command.Parameterized> event;

    public SpongeCommandRegisterEvent(
            RegisterCommandEvent<org.spongepowered.api.command.Command.Parameterized> event) {
        this.event = event;

        //
        System.out.println("----------------------SpongeCommandRegisterEvent");
        //
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        event.register(
                (PluginContainer) plugin,
                org.spongepowered.api.command.Command.builder()
                        .executor(new SpongeCommandWrapper(command::execute, command.getName()))
                        .permission(command.getPermission())
                        .shortDescription(Component.text(command.getDescription()))
                        .addParameter(Parameter.string().key("args").build())
                        .build(),
                command.getName(),
                aliases);
    }
}
