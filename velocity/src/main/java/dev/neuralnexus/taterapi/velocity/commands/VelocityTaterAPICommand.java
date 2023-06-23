package dev.neuralnexus.taterapi.velocity.commands;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.velocity.player.VelocityTaterPlayer;
import net.kyori.adventure.text.Component;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;

public class VelocityTaterAPICommand implements SimpleCommand, TaterAPICommand {
    @Override
    public void execute(Invocation invocation) {
//        runTaskAsync(() -> {
            try {
                String[] args = invocation.arguments();

                // Check if sender is a player
                if (invocation.source() instanceof Player) {
                    // Execute command as player
                    VelocityTaterPlayer player = new VelocityTaterPlayer((Player) invocation.source());
                    player.sendMessage(executeCommand(player, args));
                } else {
                    // Execute command as console
                    TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
//        });
    }
}
