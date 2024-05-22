package dev.neuralnexus.taterlib.v1_15.fabric.event.command;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_15.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.v1_15.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

/** Fabric implementation of {@link BrigadierCommandRegisterEvent}. */
public class FabricBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<ServerCommandSource> {
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final boolean dedicated;

    public FabricBrigadierCommandRegisterEvent(
            CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        this.dispatcher = dispatcher;
        this.dedicated = dedicated;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return dedicated;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<ServerCommandSource> dispatcher() {
        return dispatcher;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<ServerCommandSource> node,
            Object plugin,
            String commandName,
            String... aliases) {
        dispatcher.register(node);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(ServerCommandSource source) {
        return new FabricCommandSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer((PlayerEntity) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
