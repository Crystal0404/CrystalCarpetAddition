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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.ShulkerBoxPowerOutputExpansion;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.shulkerBoxUtils.ColourMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @WrapOperation(
            method = "getComparatorOutput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/ScreenHandler;" +
                            "calculateComparatorOutput(Lnet/minecraft/block/entity/BlockEntity;)I"
            )
    )
    private int getComparatorOutputMixin(
            BlockEntity entity,
            Operation<Integer> original
    ) {
        if (!CCASettings.ShulkerBoxPowerOutputExpansion) return original.call(entity);

        if (
                entity instanceof ShulkerBoxBlockEntity shulkerBoxBlockEntity
                        && shulkerBoxBlockEntity.getColor() == ColourMap.getSettingColour()
        ) {
            int num = 0;
            for (int i = 0; num != 15 && i < shulkerBoxBlockEntity.size(); i++) {
                ItemStack itemStack = shulkerBoxBlockEntity.getStack(i);
                if (itemStack.isEmpty()) continue;
                num += 1;
            }
            return num;
        } else {
            return original.call(entity);
        }
    }
}
