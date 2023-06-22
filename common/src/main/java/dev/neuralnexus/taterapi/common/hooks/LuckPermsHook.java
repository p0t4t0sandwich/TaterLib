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

    /**
     * Create a new LuckPermsHook
     */
    public LuckPermsHook() {
        instance = this;
        this.luckPerms = LuckPermsProvider.get();
    }

    /**
     * Get the LuckPermsHook instance
     * @return The LuckPermsHook instance
     */
    public static LuckPermsHook getInstance() {
        return instance;
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
}
