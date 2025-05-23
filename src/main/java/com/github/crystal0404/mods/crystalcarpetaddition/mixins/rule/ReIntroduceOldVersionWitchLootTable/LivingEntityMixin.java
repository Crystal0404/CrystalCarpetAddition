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

package com.github.crystal0404.mods.crystalcarpetaddition.mixins.rule.ReIntroduceOldVersionWitchLootTable;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.LootTableUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.ReloadableRegistries;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(
            method = "dropLoot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/registry/ReloadableRegistries$Lookup;getLootTable" +
                            "(Lnet/minecraft/registry/RegistryKey;)Lnet/minecraft/loot/LootTable;"
            )
    )
    private LootTable dropLootMixin(
            ReloadableRegistries.Lookup instance,
            RegistryKey<LootTable> key,
            Operation<LootTable> original
    ) {
        if ((LivingEntity) ((Object) this) instanceof WitchEntity && CCASettings.ReIntroduceOldVersionWitchLootTable) {
            RegistryWrapper.Impl<Enchantment> impl = this.getWorld().getRegistryManager().getOrThrow(
                    RegistryKeys.ENCHANTMENT
            );
            return LootTableUtils.Witch(impl);
        } else {
            return original.call(instance, key);
        }
    }
}
