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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.WhatIsThis;

import com.github.crystal0404.mods.crystalcarpetaddition.utils.CCAUtils;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;

// QWQ
@Restriction(conflict = @Condition(type = Condition.Type.TESTER, tester = CCAUtils.DisableEasterEggs.class))
@Mixin(SplashManager.class)
public abstract class Happy {
    @Shadow
    @Final
    private static Style DEFAULT_STYLE;

    @Inject(
            method = "getSplash",
            at = @At("HEAD"),
            cancellable = true
    )
    private void happyMixin(CallbackInfoReturnable<SplashRenderer> cir) {
        Calendar calendar = Calendar.getInstance();
        if (
                Math.random() < 0.2
                        && calendar.get(Calendar.MONTH) == Calendar.MARCH
                        && calendar.get(Calendar.DAY_OF_MONTH) == 25
        ) {
            cir.setReturnValue(
                    new SplashRenderer(Component.literal("Happy birthday Crystal0404!!!").setStyle(DEFAULT_STYLE))
            );
        }
    }
}
