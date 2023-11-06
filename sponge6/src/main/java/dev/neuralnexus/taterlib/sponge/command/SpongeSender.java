package dev.neuralnexus.taterlib.sponge.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/**
 * Sponge implementation of {@link Sender}
 */
public class SpongeSender implements Sender {
    private final CommandSource sender;

    public SpongeSender(CommandSource sender) {
        this.sender = sender;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return new UUID(0, 0);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return sender.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(Text.of(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
