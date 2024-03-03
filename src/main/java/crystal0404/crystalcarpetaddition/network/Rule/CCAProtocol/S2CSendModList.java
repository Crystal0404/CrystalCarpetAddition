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

package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
class S2CSendModList {
    private Collection<String> BlackList;
    private boolean Send;
    private boolean Regex;

    public S2CSendModList(Collection<String> blackList, boolean send, boolean regex) {
        BlackList = blackList;
        Send = send;
        Regex = regex;
    }

    @Override
    public String toString() {
        return "S2CSendModList{" +
                "BlackList=" + BlackList +
                ", Send=" + Send +
                ", Regex=" + Regex +
                '}';
    }
}
