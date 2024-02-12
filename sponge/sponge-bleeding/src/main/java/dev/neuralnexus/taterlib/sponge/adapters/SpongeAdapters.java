package dev.neuralnexus.taterlib.sponge.adapters;



import net.minecraft.world.entity.player.Player;

/** Adapts Sponge classes to NMS classes. */
public class SpongeAdapters {
    /**
     * Returns a Vanilla player from a Sponge player.
     *
     * @param player The Bukkit player.
     * @return The Vanilla player.
     */
    public static Player player(org.spongepowered.api.entity.living.player.Player player) {
        return (Player) player;
    }
}
