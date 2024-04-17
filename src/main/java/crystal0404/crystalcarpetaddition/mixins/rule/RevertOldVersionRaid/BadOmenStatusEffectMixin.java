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

package crystal0404.crystalcarpetaddition.mixins.rule.RevertOldVersionRaid;

import com.llamalad7.mixinextras.sugar.Local;
import crystal0404.crystalcarpetaddition.CCASettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.entity.LivingEntity;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Restriction(require = @Condition(value = "minecraft", versionPredicates = ">=1.20.5-alpha.24.14.a"))
@Mixin(targets = "net.minecraft.entity.effect.BadOmenStatusEffect")
public abstract class BadOmenStatusEffectMixin {
    @Inject(
            method = "applyUpdateEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
            ),
            cancellable = true
    )
    private void applyUpdateEffectMixin(
            LivingEntity entity, int amplifier, CallbackInfoReturnable<Boolean> cir,
            @Local(ordinal = 0) ServerWorld serverWorld,
            @Local(ordinal = 0) ServerPlayerEntity playerEntity
    ) {
        boolean bl = serverWorld.getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        if (CCASettings.RevertOldVersionRaid && bl) {
            cir.setReturnValue(this.tryStartRaid(playerEntity, serverWorld));
        }
    }

    @Unique
    private boolean tryStartRaid(ServerPlayerEntity player, ServerWorld world) {
        BlockPos pos = player.getBlockPos();
        if (world.getDifficulty() != Difficulty.PEACEFUL && world.isNearOccupiedPointOfInterest(pos)) {
            return world.getRaidManager().startRaid(player, pos) == null;
        }
        return true;
    }
}
