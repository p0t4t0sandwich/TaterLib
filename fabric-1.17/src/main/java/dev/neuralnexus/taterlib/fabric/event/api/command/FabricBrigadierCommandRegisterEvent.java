package dev.neuralnexus.taterlib.fabric.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.fabric.command.FabricSender;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * Fabric implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class FabricBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<ServerCommandSource> {
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final boolean dedicated;

    public FabricBrigadierCommandRegisterEvent(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        this.dispatcher = dispatcher;
        this.dedicated = dedicated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDedicated() {
        return dedicated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDispatcher<ServerCommandSource> getDispatcher() {
        return dispatcher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(LiteralCommandNode<ServerCommandSource> node, Object plugin, String commandName, String... aliases) {
        dispatcher.register(node.createBuilder());
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sender getSender(ServerCommandSource source) {
        return new FabricSender(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer((PlayerEntity) source.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
