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
import crystal0404.crystalcarpetaddition.settings.conditions.CEnderPearlChunkLoading;
import crystal0404.crystalcarpetaddition.settings.conditions.ComparatorCanPlaceAboveAir;
import crystal0404.crystalcarpetaddition.utils.ShulkerBoxUtils.ColourMap;

public class CCASettings {
    private static final String CCA = "CCA";
    @Rule(categories = CCA)
    //#if MC > 12001
    public static boolean MagicBox = false;
    //#else
//$$     public static boolean MagicBox = true;
    //#endif

    @Rule(
            categories = CCA,
            conditions = CEnderPearlChunkLoading.class
    )
    public static boolean CEnderPearlChunkLoading = false;

    @Rule(categories = CCA)
    public static boolean ItemShadowing = false;

    @Rule(
            categories = CCA,
            conditions = ComparatorCanPlaceAboveAir.class
    )
    public static boolean ComparatorCanPlaceAboveAir = false;

    @Rule(categories = CCA)
    public static boolean NoBatSpawn = false;

    @Rule(categories = CCA)
    public static boolean EndermanCannotPickUpBlocksInNether = false;

    @Rule(categories = CCA)
    public static boolean ShulkerBoxPowerOutputExpansion = false;

    @Rule(categories = CCA)
    public static ColourMap.Colour ShulkerBoxPowerOutputExpansionColour = ColourMap.Colour.PINK;
}
