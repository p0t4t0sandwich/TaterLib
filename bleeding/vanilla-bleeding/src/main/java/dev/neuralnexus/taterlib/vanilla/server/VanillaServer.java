package dev.neuralnexus.taterlib.vanilla.server;

import com.mojang.brigadier.CommandDispatcher;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.server.ServerStartingEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;

import org.jetbrains.annotations.ApiStatus;

import java.util.Set;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link Server}. */
public class VanillaServer implements Server {
    private static MinecraftServer server;
    private static VanillaServer instance;

    public VanillaServer(MinecraftServer server) {
        VanillaServer.server = server;
        VanillaServer.instance = this;
    }

    public VanillaServer(Object server) {
        this((MinecraftServer) server);
    }

    /**
     * Gets the instance.
     *
     * @return The instance.
     */
    public static VanillaServer getInstance() {
        return instance;
    }

    /**
     * Gets the server.
     *
     * @return The server.
     */
    @ApiStatus.Internal
    public static MinecraftServer getServer() {
        return server;
    }

    /**
     * Sets the server.
     *
     * @param server The server.
     */
    @ApiStatus.Internal
    public static void setServer(MinecraftServer server) {
        VanillaServer.server = server;
    }

    /** Registers commands TODO: REMOVE */
    @Deprecated
    public static void registerCommands(ServerStartingEvent event) {
        MinecraftServer server = getServer();

        CommandDispatcher<CommandSourceStack> dispatcher =
                server.resources.managers().getCommands().getDispatcher();
        Commands.CommandSelection commandSelection =
                server.isDedicatedServer()
                        ? Commands.CommandSelection.DEDICATED
                        : Commands.CommandSelection.INTEGRATED;

        // Register brigadier commands
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new VanillaBrigadierCommandRegisterEvent(dispatcher, commandSelection));

        // Sponge has its own, nicer simple command system
        if (!TaterAPIProvider.serverType().isSpongeBased()) {
            CommandEvents.REGISTER_COMMAND.invoke(
                    new VanillaCommandRegisterEvent(dispatcher, commandSelection));
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "local";
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> getOnlinePlayers() {
        return server.getPlayerList().getPlayers().stream()
                .map(VanillaPlayer::new)
                .collect(Collectors.toSet());
    }
}
