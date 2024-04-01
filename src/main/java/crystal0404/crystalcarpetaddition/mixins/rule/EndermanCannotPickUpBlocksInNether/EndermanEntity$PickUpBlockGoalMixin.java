package crystal0404.crystalcarpetaddition.mixins.rule.EndermanCannotPickUpBlocksInNether;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public abstract class EndermanEntity$PickUpBlockGoalMixin {
    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean tickMixin(boolean original, @Local(ordinal = 0) World world) {
        return CCASettings.EndermanCannotPickUpBlocksInNether ?
                world.getRegistryKey() != World.NETHER && original : original;
    }
}
