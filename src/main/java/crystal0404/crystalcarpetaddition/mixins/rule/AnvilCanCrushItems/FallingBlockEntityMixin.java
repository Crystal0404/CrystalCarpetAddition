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

package crystal0404.crystalcarpetaddition.mixins.rule.AnvilCanCrushItems;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Shadow
    private BlockState block;

    @Inject(
            method = "handleFallDamage",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/function/Predicate;" +
                            "and(Ljava/util/function/Predicate;)Ljava/util/function/Predicate;",
                    shift = At.Shift.BY,
                    by = 2
            )
    )
    private void handleFallDamageMixin(
            float fallDistance,
            float damageMultiplier,
            DamageSource damageSource,
            CallbackInfoReturnable<Boolean> cir,
            @Local(ordinal = 0) LocalRef<Predicate<Entity>> predicate
    ) {
        predicate.set(
                CCASettings.AnvilCanCrushItemEntities && this.block.isIn(BlockTags.ANVIL)
                        ? EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR : predicate.get()
        );
    }
}
