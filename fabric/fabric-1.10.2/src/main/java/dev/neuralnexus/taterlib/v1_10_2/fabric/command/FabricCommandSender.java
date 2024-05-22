package dev.neuralnexus.taterlib.v1_10_2.fabric.command;

import dev.neuralnexus.taterlib.command.CommandSender;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.text.TranslatableText;

import java.util.UUID;

/** The Fabric implementation of {@link CommandSender} */
public class FabricCommandSender implements CommandSender {
    private final PermissibleCommandSource source;

    public FabricCommandSender(PermissibleCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public PermissibleCommandSource sender() {
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
        return source.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        source.sendMessage(new TranslatableText(message));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }
}
