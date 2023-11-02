package dev.neuralnexus.taterlib.forge.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.commands.TaterLibCommand;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.loading.FMLLoader;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;

/**
 * Forge implementation of the TaterLib command.
 */
@Mod.EventBusSubscriber(modid = Constants.PROJECT_ID)
public final class ForgeTaterLibCommand {
    /**
     * Registers the TaterLib command.
     * @param event The register commands event.
     */
    @SubscribeEvent
    public static void registerCommand(FMLServerStartingEvent event) {
        int permissionLevel;
        if (FMLLoader.getDist().isDedicatedServer()) {
            // Check if LuckPerms is hooked
            permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;
        } else {
            permissionLevel = 0;
        }

        // Register command
        event.getCommandDispatcher().register(literal(TaterLibCommand.getCommandName())
            .requires(source -> source.hasPermissionLevel(permissionLevel))
            .then(argument("command", StringArgumentType.greedyString())
            .executes(context -> {
                try {
                    String[] args = context.getArgument("command", String.class).split(" ");

                    // Check if sender is a player
                    boolean isPlayer = context.getSource().getEntity() instanceof EntityPlayer;
                    ForgePlayer player = isPlayer ? new ForgePlayer((EntityPlayer) context.getSource().getEntity()) : null;

//                    AtomicInteger success = new AtomicInteger(1);
//                    Util.getServerExecutor().execute(() -> {
                        try {
                            // Execute command
                            TaterLibCommand.executeCommand(player, isPlayer, args);
                        } catch (Exception e) {
                            System.out.println(e);
                            e.printStackTrace();
//                            success.set(0);
                            return 0;
                        }
//                    });
//                    return success.get();
                    return 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            })
        ));
    }
}
