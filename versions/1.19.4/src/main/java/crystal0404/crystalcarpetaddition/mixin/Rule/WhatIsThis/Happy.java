/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal_0404 and contributors
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

package crystal0404.crystalcarpetaddition.mixin.Rule.WhatIsThis;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Calendar;
import java.util.Random;

@Mixin(SplashTextResourceSupplier.class)
public abstract class Happy {
    @Unique
    private final Calendar calendars = Calendar.getInstance();
    @Unique
    private final Random random = new Random();
    @ModifyReturnValue(
            method = "get",
            at = @At("RETURN")
    )
    private String SplashTextRendererMixin(String original) {
        return calendars.get(Calendar.MONTH) == Calendar.MARCH && calendars.get(Calendar.DAY_OF_MONTH) == 25 && random.nextInt(3) == 2 ?
                "Happy birthday Crystal0404!!!" :
                original;
    }
}
