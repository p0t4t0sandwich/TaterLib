/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.inventory;

import dev.neuralnexus.taterapi.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link ItemStack}. */
public class SpongeItemStack implements ItemStack {
    private final org.spongepowered.api.item.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Sponge item stack.
     *
     * @return The Sponge item stack.
     */
    public org.spongepowered.api.item.inventory.ItemStack itemStack() {
        return itemStack;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(itemStack.getItem().getType().getName());
    }

    @Override
    public int count() {
        return itemStack.getQuantity();
    }

    @Override
    public void setCount(int count) {
        itemStack.setQuantity(count);
    }

    @Override
    public ItemStack clone() {
        return new SpongeItemStack(itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        if (!itemStack.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return false;
        }
        return itemStack.get(Keys.CUSTOM_NAME_VISIBLE).get();
    }

    @Override
    public Optional<String> displayName() {
        if (!itemStack.get(Keys.DISPLAY_NAME).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(itemStack.get(Keys.DISPLAY_NAME).get().toString());
    }

    @Override
    public void setDisplayName(String name) {
        itemStack.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    @Override
    public boolean hasLore() {
        return itemStack.get(Keys.ITEM_LORE).isPresent();
    }

    @Override
    public List<String> lore() {
        if (!itemStack.get(Keys.ITEM_LORE).isPresent()) {
            return null;
        }
        List<Text> componentLore = itemStack.get(Keys.ITEM_LORE).get();
        List<String> lore = new ArrayList<>();
        for (Text text : componentLore) {
            lore.add(text.toPlain());
        }
        return lore;
    }

    @Override
    public void setLore(List<String> list) {
        List<Text> lore = new ArrayList<>();
        for (String string : list) {
            lore.add(Text.of(string));
        }
        itemStack.offer(Keys.ITEM_LORE, lore);
    }

    @Override
    public boolean hasEnchants() {
        return itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    @Override
    public boolean unbreakable() {
        return itemStack.get(Keys.UNBREAKABLE).isPresent();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.UNBREAKABLE, unbreakable);
    }
}
