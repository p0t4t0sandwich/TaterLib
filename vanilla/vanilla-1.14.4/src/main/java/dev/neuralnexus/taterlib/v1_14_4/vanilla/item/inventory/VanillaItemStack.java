/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.item.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.core.Registry;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;

/** Vanilla implementation of {@link ItemStack} */
public class VanillaItemStack implements ItemStack {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Vanilla item stack.
     */
    public VanillaItemStack(net.minecraft.world.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.world.item.ItemStack(Items.AIR) : itemStack;
    }

    /**
     * Getter for the Vanilla item stack.
     *
     * @return The Vanilla item stack.
     */
    public net.minecraft.world.item.ItemStack itemStack() {
        return itemStack;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) Registry.ITEM.getKey(itemStack.getItem());
    }

    @Override
    public int count() {
        return itemStack.getCount();
    }

    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new VanillaItemStack(itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomHoverName();
    }

    @Override
    public Optional<String> displayName() {
        if (!itemStack.hasCustomHoverName()) return Optional.empty();
        return Optional.of(itemStack.getDisplayName().getString());
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.setHoverName(new TextComponent(name));
    }

    @Override
    public boolean hasLore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<String> lore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasEnchants() {
        return itemStack.isEnchanted();
    }

    @Override
    public boolean unbreakable() {
        return itemStack.isDamageableItem();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
