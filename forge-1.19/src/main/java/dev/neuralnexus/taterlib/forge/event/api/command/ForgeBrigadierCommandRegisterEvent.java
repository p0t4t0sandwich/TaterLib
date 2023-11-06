package dev.neuralnexus.taterlib.forge.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;

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
        return event.getCommandSelection() == Commands.CommandSelection.DEDICATED;
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
    public void registerCommand(LiteralCommandNode<CommandSourceStack> node) {
        event.getDispatcher().getRoot().addChild(node);
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
        return new ForgePlayer(source.getPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
