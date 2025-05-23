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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.MagicBox;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.ModIds;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.LecternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21.1"))
@Mixin(LecternBlock.class)
public abstract class LecternBlockMixin extends BlockWithEntity {
    protected LecternBlockMixin(Settings settings) {
        super(settings);
    }

    @WrapWithCondition(
            method = "onStateReplaced",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced" +
                            "(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;" +
                            "Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V"
            )
    )
    private boolean removeOnStateReplacedMixin(
            BlockWithEntity instance,
            BlockState blockState,
            World world,
            BlockPos blockPos,
            BlockState newState,
            boolean b
    ) {
        return !CCASettings.MagicBox;
    }

    @Inject(
            method = "onStateReplaced",
            at = @At("TAIL")
    )
    private void onStateReplacedMixin(
            BlockState state,
            World world,
            BlockPos pos,
            BlockState newState,
            boolean moved,
            CallbackInfo ci
    ) {
        if (CCASettings.MagicBox) {
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}
