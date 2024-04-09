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

import crystal0404.crystalcarpetaddition.CCASettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Restriction(require = @Condition(value = "minecraft", versionPredicates = ">=1.20.5-alpha.24.14.a"))
@Mixin(Raid.class)
public abstract class RaidMixin {
    @Shadow
    public abstract World getWorld();

    @ModifyArgs(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z"
            )
    )
    private void startMixin_hasStatusEffect(Args args) {
        boolean bl = this.getWorld().getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        args.set(0, CCASettings.RevertOldVersionRaid && bl ? StatusEffects.BAD_OMEN : args.get(0));
    }

    @ModifyArgs(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Lnet/minecraft/entity/effect/StatusEffectInstance;"
            )
    )
    private void startMixin_getStatusEffect(Args args) {
        boolean bl = this.getWorld().getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        args.set(0, CCASettings.RevertOldVersionRaid && bl ? StatusEffects.BAD_OMEN : args.get(0));
    }

    @Inject(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I",
                    shift = At.Shift.BY,
                    by = 1
            )
    )
    private void startMixin_removeStatusEffect(ServerPlayerEntity serverPlayerEntity, CallbackInfoReturnable<Boolean> cir) {
        boolean bl = this.getWorld().getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        if (CCASettings.RevertOldVersionRaid && bl) {
            serverPlayerEntity.removeStatusEffect(StatusEffects.BAD_OMEN);
        }
    }
}
