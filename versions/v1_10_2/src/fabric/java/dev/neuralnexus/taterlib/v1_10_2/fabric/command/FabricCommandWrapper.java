/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.fabric.command;

// import dev.neuralnexus.taterapi.TaterAPI;
// import dev.neuralnexus.taterapi.command.Command;
// import dev.neuralnexus.taterlib.v1_7_10.vanilla.command.WrappedSender;
// import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;
//
// import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandCallable;
// import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandException;
// import net.legacyfabric.fabric.api.command.v2.lib.sponge.CommandResult;
// import net.legacyfabric.fabric.api.permission.v1.PermissibleCommandSource;
// import net.legacyfabric.fabric.api.util.Location;
// import net.minecraft.entity.living.player.PlayerEntity;
// import net.minecraft.text.LiteralText;
// import net.minecraft.text.Text;
// import net.minecraft.world.World;
//
// import org.jetbrains.annotations.Nullable;
//
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
//
/// ** Wraps a command callback into a Forge CommandBase. */
// public class FabricCommandWrapper implements CommandCallable {
//    private final Command command;
//
//    public FabricCommandWrapper(Command command) {
//        this.command = command;
//    }
//
//    @Override
//    @SuppressWarnings("RedundantThrows")
//    public CommandResult process(PermissibleCommandSource source, String arguments)
//            throws CommandException {
//        String[] args = arguments.split(" ");
//        try {
//            if (source instanceof PlayerEntity) {
//                command.execute(new WrappedPlayer((PlayerEntity) source), command.name(), args);
//            }
//            command.execute(new WrappedSender(source), command.name(), args);
//        } catch (Exception e) {
//            TaterAPI.logger().error("An exception occurred while executing a command", e);
//        }
//        return CommandResult.builder().successCount(1).build();
//    }
//
//    @Override
//    public List<String> getSuggestions(
//            PermissibleCommandSource source, String s, @Nullable Location<World> location)
//            throws CommandException {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public boolean testPermission(PermissibleCommandSource source) {
//        return command.permission().isEmpty() || source.hasPermission(command.permission());
//    }
//
//    @Override
//    public Optional<Text> getShortDescription(PermissibleCommandSource source) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Text> getHelp(PermissibleCommandSource source) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Text getUsage(PermissibleCommandSource source) {
//        return new LiteralText(command.usage());
//    }
// }
