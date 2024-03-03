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

package crystal0404.crystalcarpetaddition.mixin.Rule.ItemShadowing;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(ScreenHandler.class)
public abstract class ItemShadowingMixin {

    @WrapWithCondition(
            method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;setStack(Lnet/minecraft/item/ItemStack;)V", ordinal = 4)
    )
    private boolean RemoveMixin(Slot instance, net.minecraft.item.ItemStack stack){
        return !CCASettings.ItemShadowing;
    }
    @Inject(
            method = "internalOnSlotClick(IILnet/minecraft/screen/slot/SlotActionType;Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    //#if MC <= 12001
    //$$ private void internalOnSlotClickMixin(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci, PlayerInventory playerInventory, Slot slot3, ItemStack itemStack2){
    //$$     if (CCASettings.ItemShadowing){
    //$$         slot3.setStack(itemStack2);
    //$$     }
    //$$ }
    //#elseif MC >= 12004
    private void internalOnSlotClickMixin(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci, PlayerInventory playerInventory, ItemStack itemStack5, Slot slot){
        if (CCASettings.ItemShadowing){
            slot.setStack(itemStack5);
        }
    }
    //#endif
}
