package dev.neuralnexus.taterlib.fabric.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import net.minecraft.item.ItemStack;

public class FabricItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    public FabricItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public AbstractItemMeta getMeta() {
        return new FabricItemMeta(itemStack);
    }

    @Override
    public void setMeta(AbstractItemMeta abstractItemMeta) {
        // TODO: Implement
    }

    @Override
    public String getType() {
        return itemStack.getItem().toString();
    }

    @Override
    public int getCount() {
        return itemStack.getCount();
    }

    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }
}
