package dev.neuralnexus.taterlib.vanilla.event.block;

import dev.neuralnexus.taterlib.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Vanilla implementation of {@link BlockBreakEvent}. */
public class VanillaBlockBreakEvent extends VanillaBlockEvent implements BlockBreakEvent {
    private final CallbackInfo ci;
    private final net.minecraft.world.entity.player.Player player;

    public VanillaBlockBreakEvent(
            Level level,
            net.minecraft.world.entity.player.Player player,
            BlockPos blockPos,
            BlockState blockState,
            BlockEntity blockEntity,
            ItemStack itemStack,
            CallbackInfo ci) {
        super(level, player, blockPos, blockState, ci);
        this.player = player;
        this.ci = ci;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return ci.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        ci.cancel();
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return new VanillaPlayer(player);
    }
}
