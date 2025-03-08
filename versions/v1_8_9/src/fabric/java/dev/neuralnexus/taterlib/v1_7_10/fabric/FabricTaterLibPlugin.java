/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric;

import dev.neuralnexus.taterapi.event.api.*;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.Platforms;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.VanillaBootstrap;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.legacyfabric.fabric.api.command.v2.CommandRegistrar;
import net.legacyfabric.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.legacyfabric.fabric.api.networking.v1.ServerPlayConnectionEvents;

@SuppressWarnings("unused")
public class FabricTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        VanillaBootstrap.init();

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FABRIC)) {
            CommandRegistrar.EVENT.register(
                    (manager, dedicated) ->
                            CommandEvents.REGISTER_COMMAND.invoke(
                                    new FabricCommandRegisterEvent(manager)));

            ServerPlayConnectionEvents.JOIN.register(
                    (handler, sender, s) ->
                            PlayerEvents.LOGIN.invoke(
                                    new VanillaPlayerLoginEvent(handler.playerEntity)));
            ServerPlayConnectionEvents.DISCONNECT.register(
                    (handler, s) ->
                            PlayerEvents.LOGOUT.invoke(
                                    new VanillaPlayerLogoutEvent(handler.playerEntity)));

            ServerLifecycleEvents.SERVER_STARTING.register(
                    s -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
            ServerLifecycleEvents.SERVER_STARTED.register(
                    s -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPING.register(
                    s -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
            ServerLifecycleEvents.SERVER_STOPPED.register(
                    s -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));
        }
    }
}
