package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.common.command.CommandUtils;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.commands.CommandSourceStack;

/**
 * Forge command utilities.
 */
public class ForgeCommandUtils implements CommandUtils<CommandSourceStack> {
    private final boolean isDedicated;

    public ForgeCommandUtils(boolean isDedicated) {
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
        return new ForgeSender(source);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new ForgePlayer(source.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
