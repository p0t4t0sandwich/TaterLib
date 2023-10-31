package dev.neuralnexus.taterlib.common.inventory;

/**
 * The interface for an AbstractItemStack
 */
public interface ItemStack {
    /**
     * Get the meta of the item
     * @return The meta of the item
     */
    ItemMeta getMeta();

    /**
     * Set the meta of the item
     * @param meta The meta of the item
     */
    void setMeta(ItemMeta meta);

    /**
     * Get the type of the item
     * @return The type of the item
     */
    String getType();

    /**
     * Get the amount of the item
     * @return The amount of the item
     */
    int getCount();

    /**
     * Set the amount of the item
     * @param count The amount of the item
     */
    void setCount(int count);

    /**
     * Clone the item
     * @return The cloned item
     */
    ItemStack clone();
}
