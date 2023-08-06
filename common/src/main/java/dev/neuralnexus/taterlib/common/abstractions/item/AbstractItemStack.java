package dev.neuralnexus.taterlib.common.abstractions.item;

/**
 * The interface for an AbstractItemStack
 */
public interface AbstractItemStack {
    /**
     * Get the meta of the item
     * @return The meta of the item
     */
    AbstractItemMeta getMeta();

    /**
     * Set the meta of the item
     * @param meta The meta of the item
     */
    void setMeta(AbstractItemMeta meta);

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
}
