package dev.neuralnexus.taterlib.v1_15_1.forge.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_15_1.forge.event.player.*;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/** Listens for player events. */
public class ForgePlayerListener {
    /**
     * Called when a player finishes/progresses in an advancement.
     *
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AdvancementEvent event) {
        Advancement advancement = event.getAdvancement();

        // Fire the generic advancement event
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new ForgePlayerAdvancementEvent.AdvancementProgress(event));

        // Get the player's advancement progress
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        PlayerAdvancements playerAdvancements =
                server.getPlayerList()
                        .getPlayerAdvancements((ServerPlayerEntity) event.getEntity());
        AdvancementProgress progress = playerAdvancements.getOrStartProgress(advancement);

        // Fire the advancement finished event if the advancement is done
        DisplayInfo displayInfo = advancement.getDisplay();
        if (displayInfo != null && displayInfo.shouldAnnounceChat() && progress.isDone()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new ForgePlayerAdvancementEvent.AdvancementFinished(event));
        }
    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEvents.DEATH.invoke(new ForgePlayerDeathEvent(event));
        }
    }

    /**
     * Called when a player logs in.
     *
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEvents.LOGIN.invoke(new ForgePlayerLoginEvent(event));
    }

    /**
     * Called when a player logs out.
     *
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerEvents.LOGOUT.invoke(new ForgePlayerLogoutEvent(event));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     *
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        PlayerEvents.MESSAGE.invoke(new ForgePlayerMessageEvent(event));
    }

    /**
     * Called when a player respawns.
     *
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEvents.RESPAWN.invoke(new ForgePlayerRespawnEvent(event));
    }
}
