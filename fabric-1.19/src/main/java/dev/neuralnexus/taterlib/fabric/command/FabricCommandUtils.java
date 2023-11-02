package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.common.command.CommandUtils;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.fabric.player.FabricPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

/**
 * Fabric command utilities.
 */
public class FabricCommandUtils implements CommandUtils<ServerCommandSource> {
    private final boolean isDedicated;

    public FabricCommandUtils(boolean isDedicated) {
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
    public Sender getSender(ServerCommandSource source) {
        return new FabricSender(source);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer(source.getPlayer());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
