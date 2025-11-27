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

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

public class LootTableUtils {
    public static LootTable Witch(HolderLookup.RegistryLookup<@NotNull Enchantment> impl) {
        Holder<@NotNull Enchantment> enchantment = impl.getOrThrow(Enchantments.LOOTING);
        LootPool.Builder loot = LootPool.lootPool()
                .setBonusRolls(ConstantValue.exactly(0.0f))
                .add(
                        LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .add(
                        LootItem.lootTableItem(Items.SUGAR)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .add(
                        LootItem.lootTableItem(Items.REDSTONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .add(
                        LootItem.lootTableItem(Items.SPIDER_EYE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .add(
                        LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .add(
                        LootItem.lootTableItem(Items.GUNPOWDER)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )

                .add(
                        LootItem.lootTableItem(Items.STICK)
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(new EnchantedCountIncreaseFunction.Builder(enchantment, UniformGenerator.between(0.0f, 1.0f)))
                )
                .setRolls(UniformGenerator.between(1.0f, 3.0f));
        return LootTable.lootTable().withPool(loot).build();
    }
}
