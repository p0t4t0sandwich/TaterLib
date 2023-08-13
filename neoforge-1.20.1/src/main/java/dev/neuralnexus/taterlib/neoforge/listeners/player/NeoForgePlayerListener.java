package dev.neuralnexus.taterlib.neoforge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.neoforge.abstractions.player.NeoForgePlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player events.
 */
public class NeoForgePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     * @param event The advancement progress event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AdvancementEvent.AdvancementProgressEvent event) {
        PlayerListener.onPlayerAdvancement(new NeoForgePlayer(event.getEntity()), event.getAdvancement().getParent().getChatComponent().getString());
    }

    /**
     * Called when a player finishes an advancement.
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancementFinished(AdvancementEvent event) {
        PlayerListener.onPlayerAdvancementFinished(new NeoForgePlayer(event.getEntity()), event.getAdvancement().getDisplay().toString());
    }

    /**
     * Called when a player dies.
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player) {
            PlayerListener.onPlayerDeath(new NeoForgePlayer((Player) entity), event.getSource().getLocalizedDeathMessage(entity).getString());
        }
    }

    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerListener.onPlayerLogin(new NeoForgePlayer(event.getEntity()));
    }

    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        PlayerListener.onPlayerLogout(new NeoForgePlayer(event.getEntity()));
    }

    /**
     * Called when a player sends a message.
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        if (TaterLib.cancelChat) event.setCanceled(true);
        PlayerListener.onPlayerMessage(new NeoForgePlayer(event.getPlayer()), event.getMessage().getString(), TaterLib.cancelChat);
    }

    /**
     * Called when a player respawns.
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerListener.onPlayerRespawn(new NeoForgePlayer(event.getEntity()));
    }
}
