package dev.neuralnexus.taterlib.bukkit.abstractions.events.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.item.BukkitItemStack;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntityDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bukkit implementation of {@link AbstractEntityDeathEvent}.
 */
public class BukkitEntityDeathEvent extends BukkitEntityEvent implements AbstractEntityDeathEvent {
    private final EntityDeathEvent event;

    public BukkitEntityDeathEvent(EntityDeathEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public List<AbstractItemStack> getDrops() {
        return event.getDrops().stream().map(BukkitItemStack::new).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {
        event.getDrops().clear();
        List<ItemStack> eventDrops = event.getDrops();
        drops.forEach(item -> eventDrops.add(((BukkitItemStack) item).getItemStack()));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {
        event.getDrops().clear();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        return event.getDroppedExp();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {
        event.setDroppedExp(exp);
    }
}
