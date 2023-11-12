package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstracts a Sponge item meta to an AbstractItemMeta.
 */
public class SpongeItemMeta implements ItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDisplayName() {
        if (!itemStack.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return false;
        }
        return itemStack.get(Keys.CUSTOM_NAME_VISIBLE).get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        if (!itemStack.get(Keys.DISPLAY_NAME).isPresent()) {
            return null;
        }
        return itemStack.get(Keys.DISPLAY_NAME).get().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisplayName(String name) {
        itemStack.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasLore() {
        return itemStack.get(Keys.ITEM_LORE).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getLore() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLore(List<String> list) {
        List<Text> lore = new ArrayList<>();
        for (String string : list) {
            lore.add(Text.of(string));
        }
        itemStack.offer(Keys.ITEM_LORE, lore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEnchants() {
        return itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnbreakable() {
        return itemStack.get(Keys.UNBREAKABLE).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.UNBREAKABLE, unbreakable);
    }
}
