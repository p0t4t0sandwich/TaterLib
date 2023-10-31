package dev.neuralnexus.taterlib.forge.commands;

import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

/**
 * Forge implementation of the TaterLib command.
 */
public class ForgeTaterLibCommand extends CommandBase {
    @Override
    public String getName() {
        return TaterLibCommand.getCommandName();
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "§cUsage: /taterlib <version | reload>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        try {
            // Check if sender is a player
            boolean isPlayer = sender instanceof EntityPlayer;
            ForgePlayer player = isPlayer ? new ForgePlayer((EntityPlayer) sender) : null;

//           Util.getServerExecutor().execute(() -> {
            try {
                // Execute command
                TaterLibCommand.executeCommand(player, isPlayer, args);
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
//           });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
