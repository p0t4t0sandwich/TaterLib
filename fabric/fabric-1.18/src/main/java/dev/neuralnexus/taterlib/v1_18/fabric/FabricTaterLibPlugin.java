package dev.neuralnexus.taterlib.v1_18.fabric;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.api.TaterAPI;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.Platform;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.logger.impl.LoggerAdapter;
import dev.neuralnexus.taterlib.v1_18.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.player.VanillaPlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.server.VanillaServerStartedEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.server.VanillaServerStartingEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.server.VanillaServerStoppedEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.server.VanillaServerStoppingEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.server.VanillaServer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.commands.Commands;

import org.apache.logging.log4j.LogManager;

public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit(Object plugin, Object server, Object logger) {
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        start(
                plugin,
                server,
                new LoggerAdapter(
                        "[" + TaterLib.Constants.PROJECT_NAME + "] ",
                        TaterLib.Constants.PROJECT_ID,
                        LogManager.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPIProvider.api(Platform.FABRIC)
                .ifPresent(api -> api.setServer(VanillaServer::instance));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FABRIC)) {
            ServerLifecycleEvents.SERVER_STARTING.register(VanillaServer::setServer);
            ServerLifecycleEvents.SERVER_STOPPED.register(s -> stop());

            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, dedicated) -> {
                        Commands.CommandSelection commandSelection =
                                dedicated
                                        ? Commands.CommandSelection.DEDICATED
                                        : Commands.CommandSelection.INTEGRATED;
                        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                new VanillaBrigadierCommandRegisterEvent(
                                        dispatcher, commandSelection));
                        // Sponge has its own, nicer simple command system
                        if (!TaterAPIProvider.platform().isSpongeBased()) {
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new VanillaCommandRegisterEvent(dispatcher, commandSelection));
                        }
                    });

            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(handler.player)));
            ServerPlayConnectionEvents.DISCONNECT.register(
                    (handler, s) ->
                            PlayerEvents.LOGOUT.invoke(
                                    new VanillaPlayerLogoutEvent(handler.player)));

            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> ServerEvents.STARTING.invoke(new VanillaServerStartingEvent(s)));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new VanillaServerStartedEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new VanillaServerStoppingEvent(s)));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent(s)));
        }
    }
}
