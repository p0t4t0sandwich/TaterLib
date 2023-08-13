package dev.neuralnexus.taterlib.forge.listeners.player;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.listeners.player.PlayerListener;
import dev.neuralnexus.taterlib.forge.abstrations.player.ForgePlayer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * Listens for player events.
 */
public class ForgePlayerListener {
    /**
     * Called when a player progresses in an advancement.
     * @param event The advancement progress event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AdvancementEvent event) {
        ForgePlayer player = new ForgePlayer((Player) event.getEntity());
        Advancement advancement = event.getAdvancement();

        // Fire the generic advancement event
        PlayerListener.onPlayerAdvancement(player, advancement.getParent().getChatComponent().getString());

        // Get the player's advancement progress
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        PlayerAdvancements playerAdvancements = server.getPlayerList().getPlayerAdvancements((ServerPlayer) event.getEntity());
        AdvancementProgress progress = playerAdvancements.getOrStartProgress(advancement);

        // Fire the advancement finished event if the advancement is done
        DisplayInfo display = advancement.getDisplay();
        if (display != null && display.shouldAnnounceChat() && progress.isDone()) {
            PlayerListener.onPlayerAdvancementFinished(player, advancement.getChatComponent().getString());
        }
    }

    /**
     * Called when a player dies.
     * @param event The player death event
     */
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity instanceof Player) {
            PlayerListener.onPlayerDeath(new ForgePlayer((Player) entity), event.getSource().getLocalizedDeathMessage(entity).getString());
        }
    }

    /**
     * Called when a player logs in.
     * @param event The player login event
     */
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // Pass TaterPlayer to helper function
        PlayerListener.onPlayerLogin(new ForgePlayer(event.getPlayer()));
    }

    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        // Pass TaterPlayer to helper function
        PlayerListener.onPlayerLogout(new ForgePlayer(event.getPlayer()));
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        if (TaterLib.cancelChat) event.setCanceled(true);

        // Send message to message relay
        PlayerListener.onPlayerMessage(new ForgePlayer(event.getPlayer()), event.getMessage(), TaterLib.cancelChat);
    }

    /**
     * Called when a player respawns.
     * @param event The player respawn event
     */
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        PlayerListener.onPlayerRespawn(new ForgePlayer(event.getPlayer()));
    }
}
