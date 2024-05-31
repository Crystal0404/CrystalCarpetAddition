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

package crystal0404.crystalcarpetaddition.utils.ShulkerBoxUtils;

import com.google.common.collect.ImmutableMap;
import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.util.DyeColor;

public class ColourMap {
    // It has to be remapped because DyeColor is obfuscated and can't be used directly as a setting option
    private static final ImmutableMap<Colour, DyeColor> DYE_COLOR_IMMUTABLE_MAP = new ImmutableMap.Builder<Colour, DyeColor>()
            .put(Colour.PINK, DyeColor.PINK)
            .put(Colour.WHITE, DyeColor.WHITE)
            .put(Colour.LIGHT_GRAY, DyeColor.LIGHT_GRAY)
            .put(Colour.GRAY, DyeColor.GRAY)
            .put(Colour.BLACK, DyeColor.BLACK)
            .put(Colour.BROWN, DyeColor.BROWN)
            .put(Colour.RED, DyeColor.RED)
            .put(Colour.ORANGE, DyeColor.ORANGE)
            .put(Colour.YELLOW, DyeColor.YELLOW)
            .put(Colour.LIME, DyeColor.LIME)
            .put(Colour.GREEN, DyeColor.GREEN)
            .put(Colour.CYAN, DyeColor.CYAN)
            .put(Colour.LIGHT_BLUE, DyeColor.LIGHT_BLUE)
            .put(Colour.BLUE, DyeColor.BLUE)
            .put(Colour.PURPLE, DyeColor.PURPLE)
            .put(Colour.MAGENTA, DyeColor.MAGENTA)
            .buildOrThrow();

    public static DyeColor getSettingColour() {
        return CCASettings.ShulkerBoxPowerOutputExpansionColour == Colour.NONE ?
                null : DYE_COLOR_IMMUTABLE_MAP.get(CCASettings.ShulkerBoxPowerOutputExpansionColour);
    }

    public enum Colour {
        NONE,
        WHITE,
        LIGHT_GRAY,
        GRAY,
        BLACK,
        BROWN,
        RED,
        ORANGE,
        YELLOW,
        LIME,
        GREEN,
        CYAN,
        LIGHT_BLUE,
        BLUE,
        PURPLE,
        MAGENTA,
        PINK
    }
}
