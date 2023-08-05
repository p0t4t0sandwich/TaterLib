package dev.neuralnexus.taterlib.common.abstractions.item;

public interface AbstractItemStack {
    AbstractItemMeta getMeta();
    void setMeta(AbstractItemMeta meta);
    String getType();
    int getCount();
    void setCount(int count);
}
