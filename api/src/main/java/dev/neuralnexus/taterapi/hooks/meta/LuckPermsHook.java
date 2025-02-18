/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterapi.hooks.meta;

import dev.neuralnexus.taterapi.hooks.Hook;
import dev.neuralnexus.taterapi.hooks.PrefixManager;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;

import java.util.Optional;
import java.util.UUID;

/**
 * A hook for LuckPerms
 *
 * @see <a href="https://luckperms.net/">LuckPerms</a>
 */
public class LuckPermsHook implements Hook, PrefixManager {
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

    @Override
    public String name() {
        return "luckperms";
    }

    /**
     * Get the CachedMetaData for a player
     *
     * @param id The UUID of the player to get the CachedMetaData for
     * @return The CachedMetaData for the player
     */
    private Optional<CachedMetaData> metaData(UUID id) {
        if (this.luckPerms == null) return Optional.empty();
        User user = this.luckPerms.getUserManager().getUser(id);
        return user != null ? Optional.of(user.getCachedData().getMetaData()) : Optional.empty();
    }

    @Override
    public String prefix(UUID id) {
        return this.metaData(id).map(CachedMetaData::getPrefix).orElse("");
    }

    @Override
    public void setPrefix(UUID id, String prefix, int priority) {
        if (this.luckPerms == null) return;
        PrefixNode node = PrefixNode.builder(prefix, priority).build();
        this.luckPerms.getUserManager().modifyUser(id, user -> user.data().add(node));
    }

    @Override
    public String suffix(UUID id) {
        return this.metaData(id).map(CachedMetaData::getSuffix).orElse("");
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
        this.luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
    }

    /**
     * Get a specific meta value for a player
     *
     * @param playerUuid The UUID of the player to get the meta value for
     * @param key The key of the meta value to get
     * @return The meta value for the player
     */
    public Optional<String> meta(UUID playerUuid, String key) {
        return this.metaData(playerUuid).map(metaData -> metaData.getMetaValue(key));
    }

    /**
     * Set a specific meta value for a player
     *
     * @param playerUuid The UUID of the player to set the meta value for
     * @param key The key of the meta value to set
     * @param value The value to set the meta value to
     */
    public void setMeta(UUID playerUuid, String key, String value) {
        if (this.luckPerms == null) return;
        User user = this.luckPerms.getUserManager().getUser(playerUuid);
        if (user == null) return;
        user.data().add(MetaNode.builder(key, value).build());
        this.luckPerms.getUserManager().saveUser(user);
    }
}
