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

package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.utils.ShulkerBoxClour.ColourMap;
import top.hendrixshen.magiclib.carpet.api.annotation.Rule;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependency;


public class CCASettings {
    private static final String CCA = "CrystalCarpetAddition";
    private static final String NETWORK = "Network";
    private static final String CREATIVE = "CreativeTools";

    @Rule(categories = CCA)
    //#if MC >=12002
    public static boolean MagicBox = false;
    //#else
    //$$ public static boolean MagicBox = true;
    //#endif

    @Rule(
            categories = CCA,
            dependencies = @Dependencies(and = @Dependency(value = "minecraft", versionPredicate = "<1.20.4"))
    )
    public static boolean CEnderPearlChunkLoading = false;

    @Rule(categories = CCA)
    public static boolean ItemShadowing = false;

    @Rule(
            categories = NETWORK,
            dependencies = @Dependencies(not = {
                    @Dependency(value = "fabricproxy-lite", versionPredicate = "<2.6.0"),
                    @Dependency(value = "fabric_proxy")
            })
    )
    public static boolean CCAProtocol = false;

    @Rule(
            categories = CCA,
            dependencies = @Dependencies(and = @Dependency(value = "minecraft", versionPredicate = ">=1.20.4"))
    )
    public static boolean ComparatorCanPlaceAboveAir = false;

    @Rule(categories = CCA)
    public static boolean EndermanCannotPickUpBlocksInNether = false;

    @Rule(categories = CCA)
    public static boolean NoBatSpawn = false;

    @Rule(categories = CREATIVE)
    public static boolean ShulkerBoxPowerOutputExpansion = false;

    @Rule(categories = CREATIVE)
    public static ColourMap.Colour ShulkerBoxPowerOutputExpansionColour = ColourMap.Colour.PINK;
}
