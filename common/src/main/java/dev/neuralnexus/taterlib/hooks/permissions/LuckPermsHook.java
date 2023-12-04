package dev.neuralnexus.taterlib.hooks.permissions;

import dev.neuralnexus.taterlib.command.Sender;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.SuffixNode;

import java.util.UUID;

/**
 * A hook for LuckPerms
 *
 * @see <a href="https://luckperms.net/">LuckPerms</a>
 */
public class LuckPermsHook implements PermissionsHook {
    private static LuckPermsHook instance;
    private final LuckPerms luckPerms;

    /** Create a new hook */
    public LuckPermsHook() {
        instance = this;
        this.luckPerms = LuckPermsProvider.get();
    }

    /**
     * Get the instance
     *
     * @return The instance
     */
    public static LuckPermsHook get() {
        return instance;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "luckperms";
    }

    /**
     * Get the CachedMetaData for a player
     *
     * @param playerUuid The UUID of the player to get the CachedMetaData for
     * @return The CachedMetaData for the player
     */
    private CachedMetaData getMetaData(UUID playerUuid) {
        if (this.luckPerms == null) return null;
        User user = luckPerms.getUserManager().getUser(playerUuid);
        return user != null ? user.getCachedData().getMetaData() : null;
    }

    /**
     * Get the prefix for a player
     *
     * @param playerUuid The UUID of the player to get the prefix for
     * @return The prefix for the player
     */
    public String getPrefix(UUID playerUuid) {
        CachedMetaData metaData = getMetaData(playerUuid);
        return metaData != null ? metaData.getPrefix() : "";
    }

    /**
     * Set the prefix for a player
     *
     * @param playerUuid The UUID of the player to set the prefix for
     * @param prefix The prefix to set
     * @param priority The priority of the prefix
     */
    public void setPrefix(UUID playerUuid, String prefix, int priority) {
        if (this.luckPerms == null) return;
        SuffixNode node = SuffixNode.builder(prefix, priority).build();
        luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
    }

    /**
     * Set the prefix for a player
     *
     * @param playerUuid The UUID of the player to set the prefix for
     * @param prefix The prefix to set
     */
    public void setPrefix(UUID playerUuid, String prefix) {
        setPrefix(playerUuid, prefix, 0);
    }

    /**
     * Get the suffix for a player
     *
     * @param playerUuid The UUID of the player to get the suffix for
     * @return The suffix for the player
     */
    public String getSuffix(UUID playerUuid) {
        CachedMetaData metaData = getMetaData(playerUuid);
        return metaData != null ? metaData.getSuffix() : "";
    }

    /**
     * Set the suffix for a player
     *
     * @param playerUuid The UUID of the player to set the suffix for
     * @param suffix The suffix to set
     * @param priority The priority of the suffix
     */
    public void setSuffix(UUID playerUuid, String suffix, int priority) {
        if (this.luckPerms == null) return;
        SuffixNode node = SuffixNode.builder(suffix, priority).build();
        luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
    }

    /**
     * Set the suffix for a player
     *
     * @param playerUuid The UUID of the player to set the suffix for
     * @param suffix The suffix to set
     */
    public void setSuffix(UUID playerUuid, String suffix) {
        setSuffix(playerUuid, suffix, 0);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(Sender sender, String permission) {
        if (this.luckPerms == null) return false;
        User user = luckPerms.getUserManager().getUser(sender.getUniqueId());
        return user != null
                && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
