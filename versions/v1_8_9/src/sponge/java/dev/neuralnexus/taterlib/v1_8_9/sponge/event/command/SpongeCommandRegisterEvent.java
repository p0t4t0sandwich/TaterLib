/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.event.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.loader.Loader;
import dev.neuralnexus.taterlib.v1_8_9.sponge.command.SpongeCommandWrapper;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/** Sponge implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class SpongeCommandRegisterEvent implements CommandRegisterEvent {
    @Override
    public void registerCommand(Command command, String... aliases) {
        String[] nameAndAliases = new String[aliases.length + 1];
        nameAndAliases[0] = command.name();
        System.arraycopy(aliases, 0, nameAndAliases, 1, aliases.length);
        Sponge.getCommandManager()
                .register(
                        Loader.instance().plugin(),
                        CommandSpec.builder()
                                .executor(
                                        new SpongeCommandWrapper(command::execute, command.name()))
                                .permission(command.permission())
                                .description(Text.of(command.description()))
                                .arguments(GenericArguments.remainingJoinedStrings(Text.of("args")))
                                .build(),
                        nameAndAliases);
    }
}
