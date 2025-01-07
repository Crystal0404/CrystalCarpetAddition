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

package crystal0404.crystalcarpetaddition;

import carpet.api.settings.Rule;
import crystal0404.crystalcarpetaddition.utils.ModIds;
import crystal0404.crystalcarpetaddition.utils.shulkerBoxUtils.ColourMap;
import crystal0404.crystalcarpetaddition.utils.annotation.Condition;
import crystal0404.crystalcarpetaddition.utils.annotation.Restriction;

@SuppressWarnings("unused")
public class CCASettings {
    private static final String CCA = "CCA";
    private static final String CREATIVE = "Creative";
    private static final String MC_TWEAKS = "MCTweaks";

    /**
     * MC Tweaks
     */

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean AnvilCanCrushItemEntities = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ComparatorIgnoresStateUpdatesFromBelow = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21"))
    public static boolean EnderDragonPartCanUseEndPortal = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean EndermanCannotPickUpBlocksInNether = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21"))
    public static boolean GatewayCannotLoadingChunks = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(
            conflict = {
                    @Condition(ModIds.ANTI_SHADOW_PATCH),
                    @Condition(ModIds.CARPET_FX),
                    @Condition(ModIds.CARPET_SHADOW)
            }
    )
    public static boolean ItemShadowing = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean MagicBox = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean NoBatSpawn = false;

    @Rule(
            //#if MC >= 12005 && MC <= 12006
            //$$ categories = {CCA, MC_TWEAKS},
            //$$ conditions = crystal0404.crystalcarpetaddition.utils.CCAUtils.EnableMagicSetting.class
            //#else
            categories = {CCA, MC_TWEAKS}
            //#endif
    )
    @Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.20.5"))
    public static boolean ReIntroduceOldVersionRaid = false;

    @Rule(categories = {CCA, MC_TWEAKS})
    @Restriction(require = @Condition(value = ModIds.MC, versionPredicates = ">=1.21"))
    public static boolean ReIntroduceOldVersionWitchLootTable = false;

    /**
     * Creative Tools
     */

    @Rule(categories = {CCA, CREATIVE})
    public static boolean ShulkerBoxPowerOutputExpansion = false;

    @Rule(categories = {CCA, CREATIVE})
    public static ColourMap.Colour ShulkerBoxPowerOutputExpansionColour = ColourMap.Colour.PINK;
}
