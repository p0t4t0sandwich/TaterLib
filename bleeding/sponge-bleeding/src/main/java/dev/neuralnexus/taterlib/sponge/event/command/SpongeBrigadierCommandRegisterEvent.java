package dev.neuralnexus.taterlib.sponge.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;

import org.spongepowered.common.command.manager.SpongeCommandManager;

/** Sponge implementation of {@link BrigadierCommandRegisterEvent}. */
public class SpongeBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<Object> {
    // TODO: Abstract with VanillaGradle so CommandDispatcher<CommandSourceStack> can be used
    // TODO: During refactor, abstract the whole class to VanillaGradle
    // (should be able to handle the dedicated check and getSender/getPlayer)
    private final SpongeCommandManager commandManager;

    public SpongeBrigadierCommandRegisterEvent(SpongeCommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return true;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public CommandDispatcher<Object> getDispatcher() {
        return (CommandDispatcher<Object>) (Object) commandManager.getDispatcher();
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<Object> node,
            Object plugin,
            String commandName,
            String... aliases) {}

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(Object source) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(Object source) {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(Object source) {
        return false;
    }
}
