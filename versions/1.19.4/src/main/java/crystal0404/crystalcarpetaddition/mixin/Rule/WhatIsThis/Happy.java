package crystal0404.crystalcarpetaddition.mixin.Rule.WhatIsThis;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;

@Mixin(SplashTextResourceSupplier.class)
public abstract class Happy {
    @Unique
    Calendar calendar = Calendar.getInstance();
    @Inject(method = "get", at = @At("RETURN"), cancellable = true)
    private void SplashTextResourceSupplierMixin(CallbackInfoReturnable<String> cir){
        if (calendar.get(Calendar.MONTH) == Calendar.MARCH && calendar.get(Calendar.DAY_OF_MONTH) == 25){
            cir.setReturnValue("Happy birthday Crystal0404!!!");
        }
    }
}
