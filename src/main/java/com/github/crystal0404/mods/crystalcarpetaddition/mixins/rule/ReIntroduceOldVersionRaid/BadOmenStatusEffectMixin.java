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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionRaid;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.BadOmenMobEffect;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BadOmenMobEffect.class)
public abstract class BadOmenStatusEffectMixin {
    @Inject(
            method = "applyEffectTick",
            at = @At("HEAD"),
            cancellable = true
    )
    private void applyUpdateEffectMixin(
            ServerLevel serverWorld,
            LivingEntity entity,
            int amplifier,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!CCASettings.ReIntroduceOldVersionRaid) return;
        if (entity instanceof ServerPlayer serverPlayerEntity && !serverPlayerEntity.isSpectator()) {
            cir.setReturnValue(this.tryStartRaid(serverPlayerEntity, serverPlayerEntity.level()));
        } else {
            cir.setReturnValue(true);
        }
    }

    @Unique
    private boolean tryStartRaid(ServerPlayer player, ServerLevel world) {
        BlockPos pos = player.blockPosition();
        if (world.getDifficulty() != Difficulty.PEACEFUL && world.isVillage(pos)) {
            return world.getRaids().createOrExtendRaid(player, pos) == null;
        }
        return true;
    }
}
