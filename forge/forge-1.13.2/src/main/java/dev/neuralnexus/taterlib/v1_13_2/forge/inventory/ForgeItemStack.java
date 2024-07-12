/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.forge.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_13_2.forge.resource.ForgeResourceKey;

import net.minecraft.init.Items;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextComponentString;

import java.util.List;
import java.util.Optional;

/** Forge implementation of {@link ItemStack}. */
public class ForgeItemStack implements ItemStack {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Forge item stack.
     */
    public ForgeItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.item.ItemStack(Items.AIR) : itemStack;
    }

    /**
     * Getter for the Forge item stack.
     *
     * @return The Forge item stack.
     */
    public net.minecraft.item.ItemStack itemStack() {
        return itemStack;
    }

    @Override
    public ResourceKey type() {
        return new ForgeResourceKey(IRegistry.field_212630_s.getKey(itemStack.getItem()));
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
    public ItemStack clone() {
        return new ForgeItemStack(itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    @Override
    public Optional<String> displayName() {
        if (!itemStack.hasDisplayName()) return Optional.empty();
        return Optional.of(itemStack.getDisplayName().getString());
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.setDisplayName(new TextComponentString(name));
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
        return itemStack.isDamageable();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
