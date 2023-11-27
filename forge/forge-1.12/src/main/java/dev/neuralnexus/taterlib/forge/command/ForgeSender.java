package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

import java.util.UUID;

/** Bukkit implementation of {@link Sender} */
public class ForgeSender implements Sender {
    private final ICommandSender sender;
    private final Command command;

    public ForgeSender(ICommandSender sender, Command command) {
        this.sender = sender;
        this.command = command;
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
        sender.sendMessage(new TextComponentString(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return sender.canUseCommand(permissionLevel, command.getName());
    }
}
