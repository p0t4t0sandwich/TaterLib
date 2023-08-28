package dev.neuralnexus.taterlib.forge.commands;

import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.forge.abstrations.player.ForgePlayer;
import net.minecraft.Util;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * Forge implementation of the TaterLib command.
 */
@Mod.EventBusSubscriber(modid = ForgeTaterLibPlugin.MOD_ID)
public final class ForgeTaterLibCommand {
    /**
     * Registers the TaterLib command.
     * @param event The register commands event.
     */
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        int permissionLevel;
        if (event.getCommandSelection() == Commands.CommandSelection.DEDICATED) {
            // Check if LuckPerms is hooked
            permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;
        } else {
            permissionLevel = 0;
        }

        // Register command
        event.getDispatcher().register(literal(TaterLibCommand.getCommandName())
            .requires(source -> source.hasPermission(permissionLevel))
            .then(argument("command", StringArgumentType.greedyString())
            .executes(context -> {
                try {
                    String[] args = context.getArgument("command", String.class).split(" ");

                    // Check if sender is a player
                    boolean isPlayer = context.getSource().getEntity() instanceof Player;
                    ForgePlayer player = isPlayer ? new ForgePlayer((Player) context.getSource().getEntity()) : null;

                    AtomicInteger success = new AtomicInteger(1);
                    Util.backgroundExecutor().submit(() -> {
                        try {
                            // Execute command
                            TaterLibCommand.executeCommand(player, isPlayer, args);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
                            success.set(0);
                        }
                    });
                    return success.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            })
        ));
    }
}
