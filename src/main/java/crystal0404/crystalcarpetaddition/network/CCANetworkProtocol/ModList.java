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

package crystal0404.crystalcarpetaddition.network.CCANetworkProtocol;

import java.util.Collection;
import java.util.HashMap;

class ModList {
    private Collection<HashMap<String, String>> ModList;
    private boolean Regex;

    public ModList(Collection<HashMap<String, String>> modList, boolean regex) {
        ModList = modList;
        Regex = regex;
    }

    public ModList(Collection<HashMap<String, String>> modList) {
        ModList = modList;
    }

    @Override
    public String toString() {
        return "ModList{" +
                "ModList=" + ModList +
                ", Regex=" + Regex +
                '}';
    }

    public Collection<HashMap<String, String>> getModList() {
        return ModList;
    }

    public void setModList(Collection<HashMap<String, String>> modList) {
        ModList = modList;
    }

    public boolean isRegex() {
        return Regex;
    }

    public void setRegex(boolean regex) {
        Regex = regex;
    }
}
