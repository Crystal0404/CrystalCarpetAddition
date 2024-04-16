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

package crystal0404.crystalcarpetaddition.settings.conditions;

import carpet.api.settings.Rule;
import crystal0404.crystalcarpetaddition.utils.FabricVersionChecker;

public class ComparatorIgnoresUpdatesFromBelow implements Rule.Condition {
    @Override
    public boolean shouldRegister() {
        return FabricVersionChecker.isLoad("ComparatorCanPlaceAboveAir", "minecraft", ">=1.20.4");
    }
}
