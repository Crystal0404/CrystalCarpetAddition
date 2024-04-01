package crystal0404.crystalcarpetaddition.mixins.rule.NoBatSpwan;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.passive.BatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BatEntity.class)
public class BatEntityMixin {
    // This is achieved indirectly by modifying the sea level height
    @ModifyExpressionValue(
            method = "canSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/WorldAccess;getSeaLevel()I"
            )
    )
    private static int canSpawnMixin(int original) {
        return CCASettings.NoBatSpawn ? Integer.MIN_VALUE : original;
    }
}
