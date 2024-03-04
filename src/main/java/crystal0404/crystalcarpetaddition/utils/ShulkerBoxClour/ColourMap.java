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

package crystal0404.crystalcarpetaddition.utils.ShulkerBoxClour;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.util.DyeColor;

public class ColourMap {
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

    public static DyeColor getSettingColour() {
        DyeColor color = null;
        switch (CCASettings.ShulkerBoxPowerOutputExpansionColour) {
            case PINK:
                color = DyeColor.PINK;
                break;
            case NONE:
                break;
            case WHITE:
                color = DyeColor.WHITE;
                break;
            case LIGHT_GRAY:
                color = DyeColor.LIGHT_GRAY;
                break;
            case GRAY:
                color = DyeColor.GRAY;
                break;
            case BLACK:
                color = DyeColor.BLACK;
                break;
            case BROWN:
                color = DyeColor.BROWN;
                break;
            case RED:
                color = DyeColor.RED;
                break;
            case ORANGE:
                color = DyeColor.ORANGE;
                break;
            case YELLOW:
                color = DyeColor.YELLOW;
                break;
            case LIME:
                color = DyeColor.LIME;
                break;
            case GREEN:
                color = DyeColor.GREEN;
                break;
            case CYAN:
                color = DyeColor.CYAN;
                break;
            case LIGHT_BLUE:
                color = DyeColor.LIGHT_BLUE;
                break;
            case BLUE:
                color = DyeColor.BLUE;
                break;
            case PURPLE:
                color = DyeColor.PURPLE;
                break;
            case MAGENTA:
                color = DyeColor.MAGENTA;
                break;
        }
       return color;
    }
}
