package dev.neuralnexus.taterlib.forge.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import net.minecraft.command.CommandSource;

import java.util.UUID;

/**
 * The Forge implementation of {@link Sender}
 */
public class ForgeSender implements Sender {
    private final CommandSource source;

    public ForgeSender(CommandSource source) {
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
        return source.getEntity().getUniqueID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return source.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermissionLevel(permissionLevel);
    }
}
