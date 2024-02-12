package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

import java.util.UUID;

/** The Forge implementation of {@link CommandSender} */
public class ForgeCommandSender implements CommandSender {
    private final CommandSource source;

    public ForgeCommandSender(CommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public CommandSource sender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return source.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendFeedback(new StringTextComponent(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermissionLevel(permissionLevel);
    }
}
