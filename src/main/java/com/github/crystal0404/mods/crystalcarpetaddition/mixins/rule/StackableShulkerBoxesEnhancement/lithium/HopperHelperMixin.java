/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2025  Crystal0404 and contributors
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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.StackableShulkerBoxesEnhancement.lithium;

import carpet.CarpetSettings;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.ModIds;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.shulkerBoxUtils.ShulkerBoxesSet;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.caffeinemc.mods.lithium.common.hopper.HopperHelper;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Restriction(
        require = @Condition(value = ModIds.LITHIUM, versionPredicates = ">=0.14.0"),
        conflict = @Condition(ModIds.PCA)
)
@Mixin(HopperHelper.class)
public abstract class HopperHelperMixin {
    @WrapOperation(
            method = "tryMoveSingleItem(" +
                    "Lnet/minecraft/world/Container;" +
                    "Lnet/minecraft/world/WorldlyContainer;" +
                    "Lnet/minecraft/world/item/ItemStack;" +
                    "Lnet/minecraft/world/item/ItemStack;" +
                    "I" +
                    "Lnet/minecraft/core/Direction;" +
                    ")Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I"
            )
    )
    private static int tryMoveSingleItemMixin(ItemStack instance, Operation<Integer> original) {
        if (
                CCASettings.StackableShulkerBoxesEnhancement
                        && CarpetSettings.shulkerBoxStackSize != 1
                        && ShulkerBoxesSet.ITEMS_SET.contains(instance.getItem())
        ) {
            return 1;
        } else {
            return original.call(instance);
        }
    }
}
