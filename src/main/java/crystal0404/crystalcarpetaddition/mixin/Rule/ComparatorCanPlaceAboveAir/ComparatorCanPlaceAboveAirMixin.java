//#if MC >= 12004
//$$ package crystal0404.crystalcarpetaddition.mixin.Rule.ComparatorCanPlaceAboveAir;
//$$
//$$ import crystal0404.crystalcarpetaddition.CCASettings;
//$$ import net.minecraft.block.AbstractRedstoneGateBlock;
//$$ import net.minecraft.block.BlockEntityProvider;
//$$ import net.minecraft.block.BlockState;
//$$ import net.minecraft.block.ComparatorBlock;
//$$ import net.minecraft.util.math.BlockPos;
//$$ import net.minecraft.util.math.Direction;
//$$ import net.minecraft.world.WorldAccess;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//$$
//$$ @Mixin(ComparatorBlock.class)
//$$ public abstract class ComparatorCanPlaceAboveAirMixin extends AbstractRedstoneGateBlock implements BlockEntityProvider {
//$$     protected ComparatorCanPlaceAboveAirMixin(Settings settings) {
//$$         super(settings);
//$$     }
//$$
//$$     @Inject(method = "getStateForNeighborUpdate", at = @At(value = "HEAD"), cancellable = true)
//$$     private void ComparatorBlockMixin(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir){
//$$         if (CCASettings.ComparatorCanPlaceAboveAir) {
//$$             cir.setReturnValue(super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos));
//$$         }
//$$     }
//$$ }
//#endif
