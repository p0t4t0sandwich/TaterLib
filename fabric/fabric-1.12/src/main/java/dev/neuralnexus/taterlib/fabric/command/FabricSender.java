package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.command.Sender;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.command.ServerCommandSource;

import java.util.UUID;

/** The Fabric implementation of {@link Sender} */
public class FabricSender implements Sender {
    private final ServerCommandSource source;

    public FabricSender(ServerCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public ServerCommandSource getSender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return source.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendFeedback(new TranslatableComponent(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return source.hasPermissionLevel(permissionLevel);
    }
}
