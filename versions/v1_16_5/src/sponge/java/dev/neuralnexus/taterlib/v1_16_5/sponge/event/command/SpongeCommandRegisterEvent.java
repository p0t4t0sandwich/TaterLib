/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.event.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.v1_16_5.sponge.command.SpongeCommandWrapper;

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
    }

    @Override
    public void registerCommand(Command command, String... aliases) {
        event.register(
                (PluginContainer) Loader.instance().plugin(),
                org.spongepowered.api.command.Command.builder()
                        .executor(new SpongeCommandWrapper(command::execute, command.name()))
                        .permission(command.permission())
                        .shortDescription(Component.text(command.description()))
                        .addParameter(Parameter.string().key("args").build())
                        .build(),
                command.name(),
                aliases);
    }
}
