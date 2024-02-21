package crystal0404.crystalcarpetaddition.mixin.Rule.WhatIsThis;

import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Calendar;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class Happy {
    @Unique
    Random random = new Random();
    @Inject(method = "get", at = @At("RETURN"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void SplashTextResourceSupplierMixin(CallbackInfoReturnable<SplashTextRenderer> cir, Calendar calendar){
        if (calendar.get(Calendar.MONTH) == Calendar.MARCH && calendar.get(Calendar.DAY_OF_MONTH) == 25 && random.nextInt(3) == 2){
            cir.setReturnValue(new SplashTextRenderer("Happy birthday Crystal0404!!!"));
        }
    }
}
