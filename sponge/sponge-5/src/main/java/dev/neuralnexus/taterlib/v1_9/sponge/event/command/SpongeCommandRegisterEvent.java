package dev.neuralnexus.taterlib.v1_9.sponge.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9.sponge.command.SpongeCommandWrapper;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/** Sponge implementation of {@link CommandRegisterEvent}. */
public class SpongeCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        String[] nameAndAliases = new String[aliases.length + 1];
        nameAndAliases[0] = command.name();
        System.arraycopy(aliases, 0, nameAndAliases, 1, aliases.length);
        Sponge.getCommandManager()
                .register(
                        plugin,
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
