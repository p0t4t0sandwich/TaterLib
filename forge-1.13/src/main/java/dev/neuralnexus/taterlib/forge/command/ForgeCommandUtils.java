package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.common.command.CommandUtils;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Forge command utilities.
 */
public class ForgeCommandUtils implements CommandUtils<CommandSource> {
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
    public Sender getSender(CommandSource source) {
        return new ForgeSender(source);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer(CommandSource source) {
        return new ForgePlayer((EntityPlayer) source.getEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof EntityPlayer;
    }
}
