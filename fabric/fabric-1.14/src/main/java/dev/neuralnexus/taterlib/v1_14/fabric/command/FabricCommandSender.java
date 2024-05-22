package dev.neuralnexus.taterlib.v1_14.fabric.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.command.ServerCommandSource;

import java.util.UUID;

/** The Fabric implementation of {@link CommandSender} */
public class FabricCommandSender implements CommandSender {
    private final ServerCommandSource source;

    public FabricCommandSender(ServerCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public ServerCommandSource sender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        if (source.getEntity() == null) {
            return new UUID(0, 0);
        }
        return source.getEntity().getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
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
