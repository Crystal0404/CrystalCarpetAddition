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
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.village.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Raid.class)
public abstract class RaidMixin {
    @ModifyArg(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/network/ServerPlayerEntity;" +
                            "getStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)" +
                            "Lnet/minecraft/entity/effect/StatusEffectInstance;"
            ),
            index = 0
    )
    @SuppressWarnings("rawtypes")
    private RegistryEntry startMixin_getStatusEffect(RegistryEntry original) {
        return CCASettings.ReIntroduceOldVersionRaid ? StatusEffects.BAD_OMEN : original;
    }

    // TODO It will need to be modified in future snapshots
    // TODO In ME0.5, need to use expression refactoring
    //#if MC >= 12102
    @ModifyExpressionValue(
            method = "findRandomRaidersSpawnLocation",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/MathHelper;abs(I)I"
            )
    )
    private int findRandomRaidersSpawnLocationMixin(int original) {
        return CCASettings.ReIntroduceOldVersionRaid ? Integer.MIN_VALUE : original;
    }
    //#endif
}
