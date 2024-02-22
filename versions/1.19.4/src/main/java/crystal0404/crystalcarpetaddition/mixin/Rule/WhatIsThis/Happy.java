package crystal0404.crystalcarpetaddition.mixin.Rule.WhatIsThis;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class Happy {
    @Unique
    private final Calendar calendars = Calendar.getInstance();
    @Unique
    private final Random random = new Random();
    @Inject(method = "get", at = @At("RETURN"), cancellable = true)
    private void SplashTextResourceSupplierMixin(CallbackInfoReturnable<String> cir){
        if (calendars.get(Calendar.MONTH) == Calendar.MARCH && calendars.get(Calendar.DAY_OF_MONTH) == 25 && random.nextInt(3) == 2){
            cir.setReturnValue("Happy birthday Crystal0404!!!");
        }
    }
}
