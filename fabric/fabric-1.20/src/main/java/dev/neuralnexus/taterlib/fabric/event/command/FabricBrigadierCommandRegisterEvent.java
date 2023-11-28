package dev.neuralnexus.taterlib.fabric.event.command;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.fabric.command.FabricSender;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

/** Fabric implementation of {@link BrigadierCommandRegisterEvent}. */
public class FabricBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<ServerCommandSource> {
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final CommandRegistryAccess registryAccess;
    private final CommandManager.RegistrationEnvironment environment;

    public FabricBrigadierCommandRegisterEvent(
            CommandDispatcher<ServerCommandSource> dispatcher,
            CommandRegistryAccess registryAccess,
            CommandManager.RegistrationEnvironment environment) {
        this.dispatcher = dispatcher;
        this.registryAccess = registryAccess;
        this.environment = environment;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return environment.name().equals("DEDICATED");
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<ServerCommandSource> getDispatcher() {
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
    public Sender getSender(ServerCommandSource source) {
        return new FabricSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer(source.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
