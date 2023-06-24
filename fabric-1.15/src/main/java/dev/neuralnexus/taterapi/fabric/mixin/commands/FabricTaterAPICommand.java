package dev.neuralnexus.taterapi.fabric.mixin.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.neuralnexus.taterapi.common.TaterAPI;
import dev.neuralnexus.taterapi.common.commands.TaterAPICommand;
import dev.neuralnexus.taterapi.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterapi.fabric.player.FabricTaterPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

import static dev.neuralnexus.taterapi.common.Utils.ansiiParser;
import static dev.neuralnexus.taterapi.common.Utils.runTaskAsync;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Mixin(CommandManager.class)
public class FabricTaterAPICommand implements TaterAPICommand {
    @Shadow @Final private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;findAmbiguities(Lcom/mojang/brigadier/AmbiguityConsumer;)V"))
    private void registerTaterAPICommand(boolean isDedicatedServer, CallbackInfo ci) {
        // Check if LuckPerms is hooked
        int permissionLevel = LuckPermsHook.isHooked() ? 0 : 4;

        // Register command
        this.dispatcher.register(literal(getCommandName())
                .requires(source -> source.hasPermissionLevel(permissionLevel))
                .then(argument("subcommand", StringArgumentType.greedyString())
                        .executes(context -> {
                            AtomicInteger result = new AtomicInteger(1);
                            runTaskAsync(() -> {
                                try {
                                    String[] args = new String[] {context.getArgument("subcommand", String.class)};

                                    // Check if sender is a player
                                    Entity entity = context.getSource().getEntity();
                                    if (entity instanceof PlayerEntity) {
                                        // Execute command as player
                                        FabricTaterPlayer player = new FabricTaterPlayer((PlayerEntity) entity);
                                        player.sendMessage(executeCommand(player, args));
                                    } else {
                                        // Execute command as console
                                        TaterAPI.useLogger(ansiiParser(executeCommand(args)));
                                    }
                                } catch (Exception e) {
                                    result.set(0);
                                    System.err.println(e);
                                    e.printStackTrace();
                                }
                            });
                            return result.get();
                        })
                ));
    }

}
