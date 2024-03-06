/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal_0404 and contributors
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

package crystal0404.crystalcarpetaddition.mixin.Rule.ShulkerBoxPowerOutputExpansion;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.utils.ShulkerBoxClour.ColourMap;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScreenHandler.class)
public class ShulkerBoxPowerOutputExpansionMixin {
    @Inject(
            method = "calculateComparatorOutput(Lnet/minecraft/block/entity/BlockEntity;)I",
            at = @At(value = "RETURN", ordinal = 0),
            cancellable = true)
    private static void calculateComparatorOutputMixin(BlockEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (
                CCASettings.ShulkerBoxPowerOutputExpansion
                && entity instanceof ShulkerBoxBlockEntity
                && ((ShulkerBoxBlockEntity) entity).getColor() == ColourMap.getSettingColour()
        ) {
            Inventory inventory = (Inventory) entity;
            int num = 0;
            for (int i = 0; num != 15 && i < inventory.size(); i++) {
                ItemStack itemStack = inventory.getStack(i);
                if (itemStack.isEmpty()) continue;
                num += 1;
            }
            cir.setReturnValue(num);
        }
    }
}
