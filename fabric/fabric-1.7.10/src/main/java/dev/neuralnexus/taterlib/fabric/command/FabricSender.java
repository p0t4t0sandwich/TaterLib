package dev.neuralnexus.taterlib.fabric.command;

import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.fabric.FabricTaterLibPlugin;

import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.List;
import java.util.UUID;

/** The Fabric implementation of {@link Sender} */
public class FabricSender implements Sender {
    private final PermissibleCommandSource source;

    public FabricSender(PermissibleCommandSource source) {
        this.source = source;
    }

    /**
     * Get the sender
     *
     * @return The sender
     */
    public PermissibleCommandSource getSender() {
        return source;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        PlayerEntity player =
                ((List<PlayerEntity>) FabricTaterLibPlugin.server.getPlayerManager().players)
                        .stream()
                                .filter(
                                        p ->
                                                p.getName()
                                                        .asFormattedString()
                                                        .equals(
                                                                source.getName()
                                                                        .asFormattedString()))
                                .findFirst()
                                .orElse(null);

        if (player == null) {
            return new UUID(0, 0);
        }
        return player.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
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
