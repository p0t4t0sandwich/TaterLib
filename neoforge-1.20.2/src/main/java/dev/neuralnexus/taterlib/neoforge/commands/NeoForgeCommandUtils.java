package dev.neuralnexus.taterlib.neoforge.commands;

import dev.neuralnexus.taterlib.common.command.CommandUtils;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import net.minecraft.commands.CommandSourceStack;

/**
 * NeoForge command utilities.
 */
public class NeoForgeCommandUtils implements CommandUtils<CommandSourceStack> {
    private final boolean isDedicated;

    public NeoForgeCommandUtils(boolean isDedicated) {
        this.isDedicated = isDedicated;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isDedicated() {
        return this.isDedicated;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Sender getSender(CommandSourceStack source) {
        return new NeoForgeSender(source);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new NeoForgePlayer(source.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
