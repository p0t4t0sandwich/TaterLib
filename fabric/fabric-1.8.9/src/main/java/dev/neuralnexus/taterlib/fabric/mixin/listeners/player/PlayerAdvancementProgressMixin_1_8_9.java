package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

/** Mixin for the player advancement progress listener. */
// @Mixin(AchievementsAndCriterions.class)
// public abstract class FabricPlayerAdvancementProgressMixin {
//    @Shadow private ServerPlayerEntity owner;
//
//    /**
//     * Called when a player completes an advancement.
//     *
//     * @param advancement The advancement
//     * @param criterionName The criterion name
//     * @param cir Callback info
//     */
//    @Inject(method = "grantCriterion", at = @At("HEAD"))
//    public void onPlayerAdvancementProgress(
//            Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
//        FabricPlayerEvents.ADVANCEMENT_PROGRESS
//                .invoker()
//                .onPlayerAdvancementProgress(this.owner, advancement, criterionName);
//    }
// }
