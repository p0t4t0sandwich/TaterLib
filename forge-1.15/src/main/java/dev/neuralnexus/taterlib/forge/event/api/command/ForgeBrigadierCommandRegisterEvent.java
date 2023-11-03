package dev.neuralnexus.taterlib.forge.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/**
 * Forge implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class ForgeBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeBrigadierCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    /**
     * @inheritDoc
     */
    @Override
    public CommandDispatcher<CommandSource> getDispatcher() {
        return event.getCommandDispatcher();
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
        return new ForgePlayer((PlayerEntity) source.getEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }
}
