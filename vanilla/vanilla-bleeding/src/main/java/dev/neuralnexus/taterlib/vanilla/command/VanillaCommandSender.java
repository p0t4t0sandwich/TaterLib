package dev.neuralnexus.taterlib.vanilla.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import java.util.UUID;

/** The Vanilla implementation of {@link CommandSender} */
public class VanillaCommandSender implements CommandSender {
    private final CommandSourceStack source;

    public VanillaCommandSender(CommandSourceStack source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSourceStack getSender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return source.getTextName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendSystemMessage(Component.nullToEmpty(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermission(permissionLevel);
    }
}
