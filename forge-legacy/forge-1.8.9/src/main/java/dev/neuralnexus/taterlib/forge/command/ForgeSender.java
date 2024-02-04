package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.UUID;

/** Bukkit implementation of {@link Sender} */
public class ForgeSender implements CommandSender {
    private final ICommandSender sender;
    private final Command command;

    public ForgeSender(ICommandSender sender, Command command) {
        this.sender = sender;
        this.command = command;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public ICommandSender getSender() {
        return sender;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return new UUID(0, 0);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return sender.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        sender.addChatMessage(new ChatComponentText(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return sender.canCommandSenderUseCommand(permissionLevel, command.getName());
    }
}
