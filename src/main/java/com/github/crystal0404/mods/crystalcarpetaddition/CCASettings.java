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

package com.github.crystal0404.mods.crystalcarpetaddition;

import carpet.api.settings.Rule;
import carpet.api.settings.Validators;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.ModIds;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.Condition;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.Restriction;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.shulkerBoxUtils.ColourMap;

@SuppressWarnings("unused")
public class CCASettings {
    private static final String CCA = "CCA";
    private static final String CREATIVE = "Creative";
    private static final String MC_TWEAKS = "MCTweaks";
    private static final String PEARL = "Pearl";

    /**
     * MC Tweaks
     */

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean AnvilCanCrushItemEntities = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ComparatorIgnoresStateUpdatesFromBelow = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean EnderDragonPartCanUseEndPortal = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean EndermanCannotPickUpBlocksInNether = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean GatewayCannotLoadingChunks = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(
            conflict = {
                    @Condition(ModIds.ANTI_SHADOW_PATCH),
                    @Condition(ModIds.CARPET_FX)
            }
    )
    public static boolean ItemShadowing = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean MagicBox = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean NoBatSpawn = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ReIntroduceOldVersionRaid = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ReIntroduceOldVersionWitchLootTable = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21.4"))
    public static boolean RemoveVillagerTradeDistanceLimit = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(conflict = @Condition(ModIds.PCA))
    public static boolean StackableShulkerBoxesEnhancement = false;

    /**
     * Pearl
     */

    @Rule(categories = {CCA, PEARL})
    public static boolean RemoveHighSpeedPearls = false;

    @Rule(
            categories = {CCA, PEARL},
            options = "40",
            strict = false,
            validators = Validators.NonNegativeNumber.class
    )
    public static int RemoveHighSpeedPearlsTime = 40;

    /**
     * Creative Tools
     */

    @Rule(categories = {CCA, CREATIVE})
    public static boolean ShulkerBoxPowerOutputExpansion = false;

    @Rule(categories = {CCA, CREATIVE})
    public static ColourMap.Colour ShulkerBoxPowerOutputExpansionColour = ColourMap.Colour.PINK;
}
