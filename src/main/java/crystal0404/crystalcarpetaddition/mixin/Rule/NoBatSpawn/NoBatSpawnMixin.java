package crystal0404.crystalcarpetaddition.mixin.Rule.NoBatSpawn;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BatEntity.class)
public abstract class NoBatSpawnMixin {
    @Inject(method = "canSpawn", at = @At("HEAD"), cancellable = true)
    private static void batCanSpawnMixin(EntityType<BatEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (CCASettings.NoBatSpawn) {
            cir.setReturnValue(false);
        }
    }
}
