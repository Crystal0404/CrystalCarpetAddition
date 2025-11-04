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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.RemoveVillagerTradeDistanceLimit;

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MerchantMenu.class)
public abstract class MerchantScreenHandlerMixin {
    @Shadow
    @Final
    private Merchant trader;

    @WrapOperation(
            method = "stillValid",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/trading/Merchant;stillValid(" +
                            "Lnet/minecraft/world/entity/player/Player;" +
                            ")Z"
            )
    )
    private boolean canUseMixin(Merchant instance, Player playerEntity, Operation<Boolean> original) {
        if (CCASettings.RemoveVillagerTradeDistanceLimit) {
            return this.trader.getTradingPlayer() == playerEntity;
        } else {
            return original.call(instance, playerEntity);
        }
    }
}
