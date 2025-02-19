/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.Platforms;
import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppedEvent;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLibPlugin;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.block.ForgeBlockBreakEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.command.ForgeBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.command.ForgeCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.entity.ForgeEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.entity.ForgeEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.entity.ForgeEntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerLoginEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerLogoutEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerMessageEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.player.ForgePlayerRespawnEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.event.pluginmessage.ForgeRegisterPacketChannelsEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.networking.ModMessages;
import dev.neuralnexus.taterlib.v1_13_2.forge.resource.ForgeResourceKey;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@SuppressWarnings("unused")
public class ForgeTaterLibPlugin implements TaterLibPlugin {
    @Override
    public void onInit() {
        TaterAPIProvider.registerBuilder(ResourceKey.Builder.class, ForgeResourceKey.Builder::new);
        TaterAPIProvider.registerFactory(ResourceKey.Factory.class, ForgeResourceKey.Factory::new);

        if (MetaAPI.instance().isPrimaryPlatform(Platforms.FORGE)) {
            // Register listeners
            MinecraftForge.EVENT_BUS.<BlockEvent.BreakEvent>addListener(
                    event ->
                            BlockEvents.PLAYER_BLOCK_BREAK.invoke(new ForgeBlockBreakEvent(event)));

            MinecraftForge.EVENT_BUS.<FMLServerStartingEvent>addListener(
                    event -> {
                        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
                        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                                new ForgeBrigadierCommandRegisterEvent(event));
                    });

            MinecraftForge.EVENT_BUS.<LivingDamageEvent>addListener(
                    event -> EntityEvents.DAMAGE.invoke(new ForgeEntityDamageEvent(event)));
            MinecraftForge.EVENT_BUS.<LivingDeathEvent>addListener(
                    event -> EntityEvents.DEATH.invoke(new ForgeEntityDeathEvent(event)));
            MinecraftForge.EVENT_BUS.<LivingSpawnEvent.SpecialSpawn>addListener(
                    event -> EntityEvents.SPAWN.invoke(new ForgeEntitySpawnEvent(event)));

            MinecraftForge.EVENT_BUS.<AdvancementEvent>addListener(
                    event -> {
                        Advancement advancement = event.getAdvancement();

                        // Fire the generic advancement event
                        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                                new ForgePlayerAdvancementEvent.AdvancementProgress(event));

                        // Get the player's advancement progress
                        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                        PlayerAdvancements playerAdvancements =
                                server.getPlayerList()
                                        .getPlayerAdvancements((EntityPlayerMP) event.getEntity());
                        AdvancementProgress progress = playerAdvancements.getProgress(advancement);

                        // Fire the advancement finished event if the advancement is done
                        DisplayInfo displayInfo = advancement.getDisplay();
                        if (displayInfo != null
                                && displayInfo.shouldAnnounceToChat()
                                && progress.isDone()) {
                            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                                    new ForgePlayerAdvancementEvent.AdvancementFinished(event));
                        }
                    });
            MinecraftForge.EVENT_BUS.<LivingDeathEvent>addListener(
                    event -> {
                        if (event.getEntity() instanceof EntityPlayerMP) {
                            PlayerEvents.DEATH.invoke(new ForgePlayerDeathEvent(event));
                        }
                    });
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerLoggedInEvent>addListener(
                    event -> PlayerEvents.LOGIN.invoke(new ForgePlayerLoginEvent(event)));
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerLoggedOutEvent>addListener(
                    event -> PlayerEvents.LOGOUT.invoke(new ForgePlayerLogoutEvent(event)));
            MinecraftForge.EVENT_BUS.<ServerChatEvent>addListener(
                    EventPriority.HIGHEST,
                    event -> PlayerEvents.MESSAGE.invoke(new ForgePlayerMessageEvent(event)));
            MinecraftForge.EVENT_BUS.<PlayerEvent.PlayerRespawnEvent>addListener(
                    event -> PlayerEvents.RESPAWN.invoke(new ForgePlayerRespawnEvent(event)));

            MinecraftForge.EVENT_BUS.<FMLServerStartingEvent>addListener(
                    event -> ServerEvents.STARTING.invoke(new ServerStartingEvent() {}));
            MinecraftForge.EVENT_BUS.<FMLServerStartedEvent>addListener(
                    event -> ServerEvents.STARTED.invoke(new ServerStartedEvent() {}));
            MinecraftForge.EVENT_BUS.<FMLServerStoppingEvent>addListener(
                    event -> ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {}));
            MinecraftForge.EVENT_BUS.<FMLServerStoppedEvent>addListener(
                    event -> ServerEvents.STOPPED.invoke(new ServerStoppedEvent() {}));

            // Register plugin channels
            IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
            modEventBus.addListener(this::commonSetup);
        }
    }

    /**
     * Called when CommonSetupEvent is fired.
     *
     * @param event The event.
     */
    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkEvents.REGISTER_CHANNELS.invoke(new ForgeRegisterPacketChannelsEvent());
        ModMessages.register();
        ModMessages.clearQueue();
    }
}
