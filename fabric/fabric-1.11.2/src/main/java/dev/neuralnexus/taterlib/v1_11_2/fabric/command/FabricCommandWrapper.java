/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.fabric.command;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.v1_11_2.fabric.entity.player.FabricPlayer;

import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandCallable;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandException;
import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandResult;
import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
import net.legacyfabric.fabric.api.util.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/** Wraps a command callback into a Forge CommandBase. */
public class FabricCommandWrapper implements CommandCallable {
    private final Command command;

    public FabricCommandWrapper(Command command) {
        this.command = command;
    }

    @Override
    @SuppressWarnings("RedundantThrows")
    public CommandResult process(PermissibleCommandSource source, String arguments)
            throws CommandException {
        String[] args = arguments.split(" ");
        try {
            if (source instanceof PlayerEntity) {
                command.execute(new FabricPlayer((PlayerEntity) source), command.name(), args);
            }
            command.execute(new FabricCommandSender(source), command.name(), args);
        } catch (Exception e) {
            TaterAPI.logger().error("An exception occurred while executing a command", e);
        }
        return CommandResult.builder().successCount(1).build();
    }

    @Override
    @SuppressWarnings("RedundantThrows")
    public List<String> getSuggestions(
            PermissibleCommandSource source,
            String arguments,
            @Nullable Location<World> targetPosition)
            throws CommandException {
        return null;
    }

    @Override
    public boolean testPermission(PermissibleCommandSource source) {
        return command.permission().isEmpty() || source.hasPermission(command.permission());
    }

    @Override
    public Optional<Text> getShortDescription(PermissibleCommandSource source) {
        return Optional.empty();
    }

    @Override
    public Optional<Text> getHelp(PermissibleCommandSource source) {
        return Optional.empty();
    }

    @Override
    public Text getUsage(PermissibleCommandSource source) {
        return new TranslatableText(command.usage());
    }
}
