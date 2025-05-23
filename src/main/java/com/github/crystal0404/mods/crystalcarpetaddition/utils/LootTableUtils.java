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

package com.github.crystal0404.mods.crystalcarpetaddition.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

public class LootTableUtils {
    public static LootTable Witch(RegistryWrapper.Impl<Enchantment> impl) {
        RegistryEntry<Enchantment> enchantment = impl.getOrThrow(Enchantments.LOOTING);
        LootPool.Builder loot = LootPool.builder()
                .bonusRolls(ConstantLootNumberProvider.create(0.0f))
                .with(
                        ItemEntry.builder(Items.GLOWSTONE_DUST)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .with(
                        ItemEntry.builder(Items.SUGAR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .with(
                        ItemEntry.builder(Items.REDSTONE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .with(
                        ItemEntry.builder(Items.SPIDER_EYE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .with(
                        ItemEntry.builder(Items.GLASS_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .with(
                        ItemEntry.builder(Items.GUNPOWDER)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )

                .with(
                        ItemEntry.builder(Items.STICK)
                                .weight(2)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseLootFunction.Builder(enchantment, UniformLootNumberProvider.create(0.0f, 1.0f)))
                )
                .rolls(UniformLootNumberProvider.create(1.0f, 3.0f));
        return LootTable.builder().pool(loot).build();
    }
}
