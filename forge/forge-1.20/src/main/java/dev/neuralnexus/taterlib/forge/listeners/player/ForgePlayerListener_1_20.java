package dev.neuralnexus.taterlib.forge.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.forge.event.player.*;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Listens for player events. */
public class ForgePlayerListener_1_20 {
    /**
     * Called when a player finishes an advancement.
     *
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancementFinished(AdvancementEvent.AdvancementEarnEvent event) {
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                new ForgePlayerAdvancementEvent_1_20.AdvancementFinished(event));
    }

    /**
     * Called when a player progresses in an advancement.
     *
     * @param event The advancement progress event
     */
    @SubscribeEvent
    public void onPlayerAdvancementProgress(AdvancementEvent.AdvancementProgressEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new ForgePlayerAdvancementEvent_1_20.AdvancementProgress(event));
    }

    /**
     * Called when a player dies.
     *
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
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
