/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal0404 and contributors
 *
 * Crystal Carpet Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal Carpet Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Crystal Carpet Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

package crystal0404.crystalcarpetaddition.mixins.rule.WhatIsThis;

import com.llamalad7.mixinextras.sugar.Local;
import crystal0404.crystalcarpetaddition.utils.CCAUtils;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;
import java.util.Random;

// QWQ
@Restriction(require = @Condition(type = Condition.Type.TESTER, tester = CCAUtils.DisableEasterEggs.class))
@Mixin(SplashTextResourceSupplier.class)
public abstract class Happy {
    @Inject(
            method = "get",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Calendar;setTime(Ljava/util/Date;)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void happyMixin(CallbackInfoReturnable<String> cir, @Local(ordinal = 0) Calendar calendar) {
        if (
                calendar.get(Calendar.MONTH) == Calendar.MARCH
                        && calendar.get(Calendar.DAY_OF_MONTH) == 25
        ) {
            Random random = new Random();
            if (random.nextInt(3) == 2) cir.setReturnValue("Happy birthday Crystal0404!!!");
        }
    }
}
