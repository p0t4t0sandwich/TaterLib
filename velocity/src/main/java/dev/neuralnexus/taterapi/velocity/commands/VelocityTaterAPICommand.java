package dev.neuralnexus.taterapi.velocity.commands;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import net.kyori.adventure.text.Component;

public class VelocityTaterAPICommand implements SimpleCommand, TaterAPICommand {
    @Override
    public void execute(Invocation invocation) {
//        runTaskAsync(() -> {
            try {
                String[] args = invocation.arguments();

                // Check if sender is a player
                if ((invocation.source() instanceof Player)) {
                    Player player = (Player) invocation.source();

                    // Permission check
                    if (!player.hasPermission(getCommandPermission())) {
                        player.sendMessage(Component.text("§cYou do not have permission to use this command."));
                        return;
                    }
                    player.sendMessage(Component.text(executeCommand(args)));
                } else {
                    TaterAPI.useLogger(executeCommand(args));
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
//        });
    }
}
