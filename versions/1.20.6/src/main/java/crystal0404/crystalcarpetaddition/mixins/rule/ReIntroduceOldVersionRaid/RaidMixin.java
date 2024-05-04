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

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Raid.class)
public abstract class RaidMixin {
    @Shadow
    public abstract World getWorld();

    @WrapOperation(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;" +
                            "hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z"
            )
    )
    @SuppressWarnings("rawtypes")
    private boolean startMixin_hasStatusEffect(
            ServerPlayerEntity instance, RegistryEntry registryEntry, Operation<Boolean> original
            ) {
        boolean bl = this.getWorld().getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        if (CCASettings.ReIntroduceOldVersionRaid && bl) {
            return original.call(instance, StatusEffects.BAD_OMEN);
        } else {
            return original.call(instance, registryEntry);
        }
    }

    @WrapOperation(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;getStatusEffect" +
                            "(Lnet/minecraft/registry/entry/RegistryEntry;)" +
                            "Lnet/minecraft/entity/effect/StatusEffectInstance;"
            )
    )
    @SuppressWarnings("rawtypes")
    private StatusEffectInstance startMixin_getStatusEffect(
            ServerPlayerEntity instance, RegistryEntry registryEntry, Operation<StatusEffectInstance> original
    ) {
        boolean bl = this.getWorld().getEnabledFeatures().contains(FeatureFlags.UPDATE_1_21);
        if (CCASettings.ReIntroduceOldVersionRaid && bl) {
            return original.call(instance, StatusEffects.BAD_OMEN);
        } else {
            return original.call(instance, registryEntry);
        }
    }
}
