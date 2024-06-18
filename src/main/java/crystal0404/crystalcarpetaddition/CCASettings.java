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
import crystal0404.crystalcarpetaddition.settings.conditions.*;
import crystal0404.crystalcarpetaddition.utils.ShulkerBoxUtils.ColourMap;

public class CCASettings {
    private static final String CCA = "CCA";
    private static final String NETWORK = "Network";
    private static final String CREATIVE = "Creative";
    private static final String MC_TWEAKS = "MCTweaks";

    /**
     * MC Tweaks
     */

    //#if MC < 12004
    //$$  @Rule(categories = {CCA, MC_TWEAKS})
    //$$  public static boolean CEnderPearlChunkLoading = false;
    //#endif

    //#if MC >= 12004
    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ComparatorIgnoresStateUpdatesFromBelow = false;
    //#endif

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean EndermanCannotPickUpBlocksInNether = false;

    //#if MC >= 12100
    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean GatewayCannotLoadingChunks = false;
    //#endif

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ItemShadowing = false;

    //#if MC >= 12002
    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean MagicBox = false;
    //#else
    //$$  @Rule(categories = {CCA, MC_TWEAKS})
    //$$  public static boolean MagicBox = true;
    //#endif

    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean NoBatSpawn = false;

    //#if MC > 12006
    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ReIntroduceOldVersionRaid = false;
    //#endif

    //#if MC > 12006
    @Rule(categories = {CCA, MC_TWEAKS})
    public static boolean ReIntroduceOldVersionWitchLootTable = false;
    //#endif

    /**
     * Creative Tools
     */

    @Rule(categories = {CCA, CREATIVE})
    public static boolean ShulkerBoxPowerOutputExpansion = false;

    @Rule(categories = {CCA, CREATIVE})
    public static ColourMap.Colour ShulkerBoxPowerOutputExpansionColour = ColourMap.Colour.PINK;

    /**
     * Network
     */

    @Rule(
            categories = {CCA, NETWORK},
            conditions = CCANetworkProtocol.class
    )
    public static boolean CCANetworkProtocol = false;

    /**
     * Other
     */

    @Rule(categories = CCA)
    public static boolean CCADebug = false;
}
