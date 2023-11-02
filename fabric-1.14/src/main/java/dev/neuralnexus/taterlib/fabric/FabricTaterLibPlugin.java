package dev.neuralnexus.taterlib.fabric;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.Constants;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.TaterLibPlugin;
import dev.neuralnexus.taterlib.common.command.BasicBrigadierCommand;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import dev.neuralnexus.taterlib.common.event.api.EntityEvents;
import dev.neuralnexus.taterlib.common.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.hooks.LuckPermsHook;
import dev.neuralnexus.taterlib.fabric.command.FabricCommandUtils;
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
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * The TaterLib Fabric plugin.
 */
public class FabricTaterLibPlugin extends TemplateFabricPlugin implements TaterLibPlugin {
    /**
     * @inheritDoc
     */
    @Override
    public AbstractLogger pluginLogger() {
        return new FabricLogger( "[TaterLib] ", LogManager.getLogger(Constants.PROJECT_ID));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerHooks() {
        // Register LuckPerms hook
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            if (FabricLoader.getInstance().isModLoaded("luckperms")) {
                useLogger("LuckPerms detected, enabling LuckPerms hook.");
                TaterLib.addHook(new LuckPermsHook());
            }
        });
    }

    /**
     * @inheritDoc
     */
    @Override
    public void registerEventListeners() {
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

    /**
     * @inheritDoc
     */
    @Override
    public void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            TaterLib.setBrigadierCommandUtils(new FabricCommandUtils(dedicated));
            for (String commandName : TaterLib.getBrigadierCommands().keySet()) {
                BasicBrigadierCommand<ServerCommandSource> command = (BasicBrigadierCommand<ServerCommandSource>) TaterLib.getBrigadierCommand(commandName);
                LiteralCommandNode<ServerCommandSource> cmd = literal(commandName)
                        .executes(command)
                        .build();

                ArgumentCommandNode<ServerCommandSource, String> args = argument("args", greedyString())
//                        .suggests(command)
                        .executes(command)
                        .build();

                cmd.addChild(args);
                dispatcher.getRoot().addChild(cmd);
            }
        });
    }

    /**
     * @inheritDoc
     */
    @Override
    public void onInitializeServer() {
        // Initialize plugin data
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FabricTaterLibPlugin.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> pluginStop());
        pluginStart();
    }
}
