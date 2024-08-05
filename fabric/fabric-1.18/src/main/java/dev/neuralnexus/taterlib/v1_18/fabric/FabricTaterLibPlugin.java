/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_18.fabric;

import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStartingEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppedEventImpl;
import dev.neuralnexus.taterapi.event.server.impl.ServerStoppingEventImpl;
import dev.neuralnexus.taterapi.server.SimpleServer;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_18.fabric.hooks.permissions.FabricPermissionsHook;
import dev.neuralnexus.taterlib.v1_18.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.command.VanillaCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class FabricTaterLibPlugin implements TaterLibPlugin {
    private static MinecraftServer server;

    @Override
    public void onInit() {
        VanillaBootstrap.init();
        TaterAPIProvider.addHook(new FabricPermissionsHook());
        start();
        TaterAPIProvider.api(Platform.FABRIC)
                .ifPresent(api -> api.setServer(() -> (SimpleServer) server));

        if (TaterAPIProvider.isPrimaryPlatform(Platform.FABRIC)) {
            ServerLifecycleEvents.SERVER_STARTING.register(s -> server = s);
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
                    s -> ServerEvents.STARTING.invoke(new ServerStartingEventImpl()));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new ServerStartedEventImpl()));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new ServerStoppingEventImpl()));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new ServerStoppedEventImpl()));
        }
    }
}
