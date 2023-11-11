package dev.neuralnexus.taterlib.forge.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;

import static net.minecraft.commands.Commands.literal;

/**
 * Forge implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class ForgeBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final RegisterCommandsEvent event;

    public ForgeBrigadierCommandRegisterEvent(RegisterCommandsEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDedicated() {
        return event.getEnvironment() == Commands.CommandSelection.DEDICATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDispatcher<CommandSourceStack> getDispatcher() {
        return event.getDispatcher();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(LiteralArgumentBuilder<CommandSourceStack> node, Object plugin, String commandName, String... aliases) {
        event.getDispatcher().register(node);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sender getSender(CommandSourceStack source) {
        return new ForgeSender(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new ForgePlayer((net.minecraft.world.entity.player.Player) source.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
