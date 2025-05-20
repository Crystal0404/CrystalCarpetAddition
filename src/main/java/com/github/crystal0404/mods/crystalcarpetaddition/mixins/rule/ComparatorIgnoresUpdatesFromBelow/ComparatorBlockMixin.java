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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.ComparatorIgnoresUpdatesFromBelow;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import net.minecraft.block.ComparatorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ComparatorBlock.class)
public abstract class ComparatorBlockMixin {
    @ModifyExpressionValue(
            method = "getStateForNeighborUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/ComparatorBlock;canPlaceAbove(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"
            )
    )
    private boolean getStateForNeighborUpdateMixin(boolean original) {
        return CCASettings.ComparatorIgnoresStateUpdatesFromBelow || original;
    }
}
