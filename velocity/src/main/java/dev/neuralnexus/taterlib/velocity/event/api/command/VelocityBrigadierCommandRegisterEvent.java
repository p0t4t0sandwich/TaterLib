package dev.neuralnexus.taterlib.velocity.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Velocity implementation of {@link CommandRegisterEvent}.
 */
public class VelocityBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<CommandSource> {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDedicated() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDispatcher<CommandSource> getDispatcher() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sender getSender(CommandSource source) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(CommandSource source) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(CommandSource source) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(LiteralArgumentBuilder<CommandSource> node, Object plugin, String commandName, String... aliases) {
        CommandManager commandManager = ((ProxyServer) plugin).getCommandManager();
        CommandMeta commandMeta = commandManager.metaBuilder(commandName)
                .aliases(aliases)
                .plugin(plugin)
                .build();
        commandManager.register(commandMeta, new BrigadierCommand(node));
    }
}
