//#if MC<12004
//$$ package crystal0404.crystalcarpetaddition.mixin.Rule.CEnderPearlChunkLoading;
//$$
//$$ import crystal0404.crystalcarpetaddition.CCASettings;
//$$ import crystal0404.crystalcarpetaddition.carpet_extra.utils.ChunkUtils;
//$$ import net.minecraft.entity.Entity;
//$$ import net.minecraft.entity.EntityType;
//$$ import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
//$$ import net.minecraft.entity.projectile.thrown.ThrownEntity;
//$$ import net.minecraft.world.World;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$
//$$ @Mixin(ThrownEntity.class)
//$$ public abstract class CEnderPearlChunkLoadingMixin extends Entity {
//$$
//$$     public CEnderPearlChunkLoadingMixin(EntityType<?> type, World world) {
//$$         super(type, world);
//$$     }
//$$     @Inject(method = "tick()V", at = @At("HEAD"))
//$$     private void ThrownEntityMixin(CallbackInfo ci){
//$$         if(CCASettings.CEnderPearlChunkLoading &&
//$$                 ((Object) this) instanceof EnderPearlEntity){
//$$             ChunkUtils.addEnderPearlChunkTicket(this);
//$$         }
//$$     }
//$$ }
//#endif