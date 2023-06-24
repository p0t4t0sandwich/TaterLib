package dev.neuralnexus.taterapi.common.hooks;

import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;

/**
 * A hook for LuckPerms
 * @see <a href="https://luckperms.net/">LuckPerms</a>
 */
public class LuckPermsHook {
    private final LuckPerms luckPerms;
    private static LuckPermsHook instance;
    private static boolean isHooked = false;

    /**
     * Create a new LuckPermsHook
     */
    public LuckPermsHook() {
        instance = this;
        this.luckPerms = LuckPermsProvider.get();
        isHooked = true;
    }

    /**
     * Get the LuckPermsHook instance
     * @return The LuckPermsHook instance
     */
    public static LuckPermsHook getInstance() {
        return instance;
    }

    /**
     * Check if the hook is enabled
     * @return If the hook is enabled
     */
    public static boolean isHooked() {
        return isHooked;
    }

    /**
     * Get the CachedMetaData for a player
     * @param taterPlayer The player to get the CachedMetaData for
     * @return The CachedMetaData for the player
     */
    private CachedMetaData getMetaData(TaterPlayer taterPlayer) {
        if (this.luckPerms == null) return null;
        User user = luckPerms.getUserManager().getUser(taterPlayer.getUUID());
        return user != null ? user.getCachedData().getMetaData() : null;
    }

    /**
     * Get the prefix for a player
     * @param taterPlayer The player to get the prefix for
     * @return The prefix for the player
     */
    public String getPrefix(TaterPlayer taterPlayer) {
        CachedMetaData metaData = getMetaData(taterPlayer);
        return metaData != null ? metaData.getPrefix() : "";
    }

    /**
     * Get the suffix for a player
     * @param taterPlayer The player to get the suffix for
     * @return The suffix for the player
     */
    public String getSuffix(TaterPlayer taterPlayer) {
        CachedMetaData metaData = getMetaData(taterPlayer);
        return metaData != null ? metaData.getSuffix() : "";
    }

    /**
     * Player has permission
     * @param taterPlayer The player to check
     * @param permission The permission to check
     * @return If the player has the permission
     */
    public boolean playerHasPermission(TaterPlayer taterPlayer, String permission) {
        if (this.luckPerms == null) return false;
        User user = luckPerms.getUserManager().getUser(taterPlayer.getUUID());
        return user != null && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
