package dev.neuralnexus.taterlib.v1_10_2.fabric.mixin.listeners.player;

/** Mixin for the player advancement finished listener. */
// @Mixin(PlayerAdvancementTracker.class)
// public abstract class FabricPlayerAdvancementFinishedMixin {
//    @Shadow private ServerPlayerEntity owner;
//
//    @Shadow
//    public abstract AdvancementProgress getProgress(Advancement advancement);
//
//    /**
//     * Called when a player completes an advancement.
//     *
//     * @param advancement The advancement
//     * @param ci Callback info
//     */
//    @Inject(method = "endTrackingCompleted", at = @At("HEAD"))
//    public void onPlayerAdvancementFinished(Advancement advancement, CallbackInfo ci) {
//        if (advancement.getDisplay() != null) {
//            AdvancementDisplay display = advancement.getDisplay();
//            if (display.shouldAnnounceToChat() && getProgress(advancement).isDone()) {
//                FabricPlayerEvents.ADVANCEMENT_FINISHED
//                        .invoker()
//                        .onPlayerAdvancementFinished(this.owner, advancement);
//            }
//        }
//    }
// }
