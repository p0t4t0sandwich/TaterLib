package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.common.command.Sender;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.UUID;

/**
 * The Fabric implementation of {@link Sender}
 */
public class FabricSender implements Sender {
    private final ServerCommandSource source;

    public FabricSender(ServerCommandSource source) {
        this.source = source;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUuid();
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
    public void sendMessage(String message) {
        source.sendMessage(Text.of(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermissionLevel(permissionLevel);
    }
}
