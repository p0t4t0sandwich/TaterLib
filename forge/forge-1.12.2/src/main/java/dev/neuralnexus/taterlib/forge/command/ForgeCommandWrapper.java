package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Forge CommandBase. */
public class ForgeCommandWrapper extends CommandBase {
    private final Command command;
    private final List<String> aliases;

    public ForgeCommandWrapper(Command command, String... aliases) {
        this.command = command;
        this.aliases = Arrays.stream(aliases).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return command.name();
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return command.usage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
            throws CommandException {
        if (sender instanceof EntityPlayer) {
            command.execute(new ForgePlayer((EntityPlayer) sender), command.name(), args);
        } else {
            command.execute(new ForgeCommandSender(sender, command), command.name(), args);
        }
    }
}
