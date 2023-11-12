package dev.neuralnexus.taterlib.fabric;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.api.TaterAPI;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.common.event.api.EntityEvents;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.fabric.event.api.command.FabricBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.fabric.event.api.command.FabricCommandRegisterEvent;
import dev.neuralnexus.taterlib.fabric.event.api.entity.FabricEntityDamageEvent;
import dev.neuralnexus.taterlib.fabric.event.api.entity.FabricEntityDeathEvent;
import dev.neuralnexus.taterlib.fabric.event.api.entity.FabricEntitySpawnEvent;
import dev.neuralnexus.taterlib.fabric.event.api.player.*;
import dev.neuralnexus.taterlib.fabric.event.api.server.FabricServerStartedEvent;
import dev.neuralnexus.taterlib.fabric.event.api.server.FabricServerStartingEvent;
import dev.neuralnexus.taterlib.fabric.event.api.server.FabricServerStoppedEvent;
import dev.neuralnexus.taterlib.fabric.event.api.server.FabricServerStoppingEvent;
import dev.neuralnexus.taterlib.fabric.logger.FabricLogger;
import dev.neuralnexus.taterlib.fabric.event.api.entity.FabricEntityEvents;
import dev.neuralnexus.taterlib.fabric.server.FabricServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import org.slf4j.LoggerFactory;

/**
 * The Fabric plugin.
 */
public class FabricTaterLibPlugin implements ModInitializer, TaterLibPlugin {
    public static MinecraftServer server;

    public FabricTaterLibPlugin() {
        TaterAPIProvider.register("config",
                FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString());
        pluginStart(this, new FabricLogger( "[" + TaterLib.Constants.PROJECT_NAME + "] ", LoggerFactory.getLogger(TaterLib.Constants.PROJECT_ID)));
        TaterAPI api = TaterAPIProvider.get();
        api.setIsPluginLoaded((plugin) ->FabricLoader.getInstance().isModLoaded(plugin));
        api.setServer(() -> new FabricServer(server));

        // Initialize plugin data
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FabricTaterLibPlugin.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());

        // Register Fabric API command events
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            CommandEvents.REGISTER_COMMAND.invoke(new FabricCommandRegisterEvent(dispatcher, dedicated));
            CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(new FabricBrigadierCommandRegisterEvent(dispatcher, dedicated));
        });

        // Register Fabric API player events
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> PlayerEvents.LOGIN.invoke(new FabricPlayerLoginEvent(handler, sender, server)));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> PlayerEvents.LOGOUT.invoke(new FabricPlayerLogoutEvent(handler, server)));

        // Register Fabric API server events
        ServerLifecycleEvents.SERVER_STARTING.register(server -> ServerEvents.STARTING.invoke(new FabricServerStartingEvent()));
        ServerLifecycleEvents.SERVER_STARTED.register(server -> ServerEvents.STARTED.invoke(new FabricServerStartedEvent()));
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> ServerEvents.STOPPING.invoke(new FabricServerStoppingEvent()));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> ServerEvents.STOPPED.invoke(new FabricServerStoppedEvent()));

        // Register TaterLib Entity events
        FabricEntityEvents.DAMAGE.register((entity, damageSource, damage, ci)  -> EntityEvents.DAMAGE.invoke(new FabricEntityDamageEvent(entity, damageSource, damage, ci)));
        FabricEntityEvents.DEATH.register((entity, source) -> EntityEvents.DEATH.invoke(new FabricEntityDeathEvent(entity, source)));
        FabricEntityEvents.SPAWN.register((entity, cir) -> EntityEvents.SPAWN.invoke(new FabricEntitySpawnEvent(entity, cir)));

        // Register TaterLib Player events
        FabricPlayerEvents.ADVANCEMENT_FINISHED.register((player, advancement) -> PlayerEvents.ADVANCEMENT_FINISHED.invoke(new FabricPlayerAdvancementEvent.AdvancementFinished(player, advancement)));
        FabricPlayerEvents.ADVANCEMENT_PROGRESS.register((player, advancement, criterionName) -> PlayerEvents.ADVANCEMENT_PROGRESS.invoke(new FabricPlayerAdvancementEvent.AdvancementProgress(player, advancement, criterionName)));
        FabricPlayerEvents.DEATH.register((player, source) -> PlayerEvents.DEATH.invoke(new FabricPlayerDeathEvent(player, source)));
        FabricPlayerEvents.MESSAGE.register((player, message, ci) -> PlayerEvents.MESSAGE.invoke(new FabricPlayerMessageEvent(player, message, ci)));
        FabricPlayerEvents.RESPAWN.register(((player, alive) -> PlayerEvents.RESPAWN.invoke(new FabricPlayerRespawnEvent(player, alive))));
    }

    @Override
    public void onInitialize() {}
}
