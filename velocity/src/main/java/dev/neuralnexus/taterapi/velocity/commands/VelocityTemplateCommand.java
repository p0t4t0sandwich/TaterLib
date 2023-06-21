package dev.neuralnexus.taterapi.velocity.commands;

import dev.neuralnexus.taterapi.velocity.VelocityMain;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;

public class VelocityTemplateCommand implements SimpleCommand {
    private final VelocityMain plugin = VelocityMain.getInstance();

    @Override
    public void execute(Invocation invocation) {
//        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                if ((invocation.source() instanceof Player)) {
                    Player player = (Player) invocation.source();

                    // Permission check
                    if (!player.hasPermission("template.command")) {
                        player.sendMessage(Component.text("§cYou do not have permission to use this command."));
                        return;
                    }

                    String text = "";

                    player.sendMessage(Component.text(text));
                } else {
                    plugin.getLogger().info("§cYou must be a player to use this command.");
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
//        });
    }
}
