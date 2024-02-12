package dev.neuralnexus.taterlib.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeBrigadierCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return event.getCommandDispatcher();
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node,
            Object plugin,
            String commandName,
            String... aliases) {
        event.getCommandDispatcher().register(node);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender sender(CommandSource source) {
        return new ForgeCommandSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player player(CommandSource source) {
        return new ForgePlayer((PlayerEntity) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
