package dev.neuralnexus.taterlib.sponge.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstracts a Sponge item meta to an AbstractItemMeta.
 */
public class SpongeItemMeta implements AbstractItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
        return itemStack.get(Keys.CUSTOM_NAME).isPresent();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        if (!itemStack.get(Keys.CUSTOM_NAME).isPresent()) {
            return null;
        }
        return itemStack.get(Keys.CUSTOM_NAME).get().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisplayName(String name) {
        itemStack.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasLore() {
        return itemStack.get(Keys.LORE).isPresent();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getLore() {
        if (!itemStack.get(Keys.LORE).isPresent()) {
            return null;
        }
        List<Component> componentLore = itemStack.get(Keys.LORE).get();
        List<String> lore = new ArrayList<>();
        for (Component component : componentLore) {
            lore.add(component.toString());
        }
        return lore;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLore(List<String> list) {
        List<Component> lore = new ArrayList<>();
        for (String string : list) {
            lore.add(Component.text(string));
        }
        itemStack.offer(Keys.LORE, lore);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasEnchants() {
        return itemStack.get(Keys.STORED_ENCHANTMENTS).isPresent();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        return itemStack.get(Keys.IS_UNBREAKABLE).isPresent();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.offer(Keys.IS_UNBREAKABLE, unbreakable);
    }
}
