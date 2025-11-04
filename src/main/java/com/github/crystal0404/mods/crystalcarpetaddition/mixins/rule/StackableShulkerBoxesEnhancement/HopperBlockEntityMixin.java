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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.StackableShulkerBoxesEnhancement;

import carpet.CarpetSettings;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.ModIds;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.shulkerBoxUtils.ShulkerBoxesSet;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Restriction(conflict = @Condition(ModIds.PCA))
@Mixin(HopperBlockEntity.class)
public abstract class HopperBlockEntityMixin {
    @WrapOperation(
            method = "tryMoveInItem(" +
                    "Lnet/minecraft/world/Container;" +
                    "Lnet/minecraft/world/Container;" +
                    "Lnet/minecraft/world/item/ItemStack;" +
                    "I" +
                    "Lnet/minecraft/core/Direction;" +
                    ")Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/HopperBlockEntity;canMergeItems(" +
                            "Lnet/minecraft/world/item/ItemStack;" +
                            "Lnet/minecraft/world/item/ItemStack;" +
                            ")Z"
            )
    )
    private static boolean transferMixin(ItemStack first, ItemStack second, Operation<Boolean> original) {
        if (
                CCASettings.StackableShulkerBoxesEnhancement
                        && CarpetSettings.shulkerBoxStackSize != 1
                        && ShulkerBoxesSet.ITEMS_SET.contains(first.getItem())
        ) {
            return false;
        } else {
            return original.call(first, second);
        }
    }
}
