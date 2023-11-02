package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import net.minecraft.commands.CommandSourceStack;

import java.util.UUID;

/**
 * The Forge implementation of {@link Sender}
 */
public class ForgeSender implements Sender {
    private final CommandSourceStack source;

    public ForgeSender(CommandSourceStack source) {
        this.source = source;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUUID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return source.getTextName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermission(permissionLevel);
    }
}
