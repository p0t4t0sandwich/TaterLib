package dev.neuralnexus.taterlib.v1_19_2.forge.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.UUID;

/** The Forge implementation of {@link CommandSender} */
public class ForgeCommandSender implements CommandSender {
    private final CommandSourceStack source;

    public ForgeCommandSender(CommandSourceStack source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSourceStack sender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return source.getTextName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendSuccess(Component.nullToEmpty(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermission(permissionLevel);
    }
}
