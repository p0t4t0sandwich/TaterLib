package dev.neuralnexus.taterlib.bukkit.event.entity;

import dev.neuralnexus.taterlib.bukkit.inventory.BukkitItemStack;
import dev.neuralnexus.taterlib.event.entity.EntityDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link EntityDeathEvent}. */
public class BukkitEntityDeathEvent extends BukkitEntityEvent implements EntityDeathEvent {
    private final org.bukkit.event.entity.EntityDeathEvent event;

    public BukkitEntityDeathEvent(org.bukkit.event.entity.EntityDeathEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public List<ItemStack> drops() {
        return event.getDrops().stream().map(BukkitItemStack::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {
        event.getDrops().clear();
        List<org.bukkit.inventory.ItemStack> eventDrops = event.getDrops();
        drops.forEach(item -> eventDrops.add(((BukkitItemStack) item).itemStack()));
    }

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {
        event.getDrops().clear();
    }

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}
}
