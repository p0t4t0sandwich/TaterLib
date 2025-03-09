/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link ItemStack}. */
public class SpongeItemStack
        implements ItemStack, Wrapped<org.spongepowered.api.item.inventory.ItemStack> {
    private final org.spongepowered.api.item.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        this.itemStack =
                itemStack == null
                        ? org.spongepowered.api.item.inventory.ItemStack.of(ItemTypes.STONE, 0)
                        : itemStack;
    }

    @Override
    public org.spongepowered.api.item.inventory.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(this.itemStack.getItem().getType().getName());
    }

    @Override
    public int count() {
        return this.itemStack.getQuantity();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setQuantity(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new SpongeItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        if (!this.itemStack.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return false;
        }
        return this.itemStack.get(Keys.CUSTOM_NAME_VISIBLE).get();
    }

    @Override
    public Optional<String> displayName() {
        if (!this.itemStack.get(Keys.DISPLAY_NAME).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(this.itemStack.get(Keys.DISPLAY_NAME).get().toString());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    @Override
    public boolean hasLore() {
        return this.itemStack.get(Keys.ITEM_LORE).isPresent();
    }

    @Override
    public List<String> lore() {
        if (!this.itemStack.get(Keys.ITEM_LORE).isPresent()) {
            return null;
        }
        List<Text> componentLore = this.itemStack.get(Keys.ITEM_LORE).get();
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
        this.itemStack.offer(Keys.ITEM_LORE, lore);
    }

    @Override
    public boolean hasEnchants() {
        return this.itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    @Override
    public boolean unbreakable() {
        return this.itemStack.get(Keys.UNBREAKABLE).isPresent();
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.itemStack.offer(Keys.UNBREAKABLE, unbreakable);
    }
}
