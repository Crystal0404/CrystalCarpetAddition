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

import com.github.crystal0404.mods.crystalcarpetaddition.CCASettings;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.LootTableUtils;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @WrapOperation(
            method = "dropFromLootTable(" +
                    "Lnet/minecraft/server/level/ServerLevel;" +
                    "Lnet/minecraft/world/damagesource/DamageSource;" +
                    "Z" +
                    "Lnet/minecraft/resources/ResourceKey;" +
                    "Ljava/util/function/Consumer;" +
                    ")V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/ReloadableServerRegistries$Holder;getLootTable(" +
                            "Lnet/minecraft/resources/ResourceKey;" +
                            ")Lnet/minecraft/world/level/storage/loot/LootTable;"
            )
    )
    @SuppressWarnings("resource")
    private LootTable dropLootMixin(
            ReloadableServerRegistries.Holder instance,
            ResourceKey<LootTable> key,
            Operation<LootTable> original
    ) {
        if ((LivingEntity) ((Object) this) instanceof Witch && CCASettings.ReIntroduceOldVersionWitchLootTable) {
            HolderLookup.RegistryLookup<Enchantment> impl = this.level().registryAccess().lookupOrThrow(
                    Registries.ENCHANTMENT
            );
            return LootTableUtils.Witch(impl);
        } else {
            return original.call(instance, key);
        }
    }
}
