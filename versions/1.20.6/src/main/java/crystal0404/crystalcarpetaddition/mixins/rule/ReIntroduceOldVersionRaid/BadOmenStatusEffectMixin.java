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

package crystal0404.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionRaid;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import crystal0404.crystalcarpetaddition.CCASettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.entity.effect.BadOmenStatusEffect")
public abstract class BadOmenStatusEffectMixin {
    @ModifyExpressionValue(
            method = "applyUpdateEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/resource/featuretoggle/FeatureSet;" +
                            "contains(Lnet/minecraft/resource/featuretoggle/FeatureFlag;)Z"
            )
    )
    private boolean applyUpdateEffectMixin(boolean original) {
        if (!original || !CCASettings.ReIntroduceOldVersionRaid) {
            return original;
        } else {
            return false;
        }
    }
}
