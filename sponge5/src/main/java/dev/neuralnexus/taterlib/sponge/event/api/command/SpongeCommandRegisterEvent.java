package dev.neuralnexus.taterlib.sponge.event.api.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.sponge.command.SpongeCommandWrapper;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

/**
 * Sponge implementation of {@link CommandRegisterEvent}.
 */
public class SpongeCommandRegisterEvent implements CommandRegisterEvent {
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        String[] nameAndAliases = new String[aliases.length + 1];
        nameAndAliases[0] = command.getName();
        System.arraycopy(aliases, 0, nameAndAliases, 1, aliases.length);
        Sponge.getCommandManager().register(plugin,
                CommandSpec
                        .builder()
                        .executor(new SpongeCommandWrapper(command::execute, command.getName()))
                        .permission(command.getPermission())
                        .description(Text.of(command.getDescription()))
                        .arguments(GenericArguments.remainingJoinedStrings(Text.of("args")))
                        .build(),
                nameAndAliases);
    }
}
