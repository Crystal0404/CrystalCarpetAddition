package crystal0404.crystalcarpetaddition.mixin.Rule.MagicBox;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlock.class)
public abstract class MagicBoxMixin {
    @Inject(method = "getComparatorOutput", at = @At("HEAD"), cancellable = true)
    private void ShulkerBoxBlockMixin(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<Integer> cir){
        if(CCASettings.MagicBox){
            cir.setReturnValue(ScreenHandler.calculateComparatorOutput((Inventory)world.getBlockEntity(pos)));
        }
        cir.setReturnValue(ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos)));
    }
}
