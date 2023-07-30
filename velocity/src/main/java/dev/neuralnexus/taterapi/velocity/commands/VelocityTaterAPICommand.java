package dev.neuralnexus.taterapi.velocity.commands;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.velocity.player.VelocityPlayer;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;

public class VelocityTaterAPICommand implements SimpleCommand {
    @Override
    public void execute(Invocation invocation) {
//        runTaskAsync(() -> {
        try {
            String[] args = invocation.arguments();

            // Check if sender is a player
            boolean isPlayer = invocation.source() instanceof Player;
            VelocityPlayer player = isPlayer ? new VelocityPlayer((Player) invocation.source()) : null;

            // Execute command
            TaterAPICommand.executeCommand(player, isPlayer, args);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
//        });
    }
}
