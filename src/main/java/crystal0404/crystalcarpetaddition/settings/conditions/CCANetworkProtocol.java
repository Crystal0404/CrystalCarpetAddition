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
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import crystal0404.crystalcarpetaddition.utils.CCAUtils;
import crystal0404.crystalcarpetaddition.utils.ModIds;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class CCANetworkProtocol implements Rule.Condition {
    @Override
    public boolean shouldRegister() {
        if (!CCAUtils.isLoad("CCANetworkProtocol", ModIds.FABRIC_NETWORK_API, "*")) return false;
        if (CCAUtils.isLoad("fabricproxy-lite", "<2.7.0")) {
            CrystalCarpetAdditionMod.LOGGER.warn(
                    "You look like you're using Velocity in Minecraft versions earlier than 1.20.2,"
                            + "Some features of CCANetworkProtocol may not work,"
                            + "and you may need to set the \"Kick\" in the \"CrystalCarpetAddition.json\" to false"
            );
        }
        if (CCAUtils.isLoad("fabricproxy", "*")) {
            CrystalCarpetAdditionMod.LOGGER.warn(
                    "You look like you're using Waterfall or BungeeCord"
                            + "CCANetworkProtocol may not work properly,"
                            + "please consider switching to Velocity or disabling CCANetworkProtocol"
            );
        }
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            CrystalCarpetAdditionMod.LOGGER.warn("[CCA] Rule \"CCANetworkProtocol\" is disabled, Because it doesn't run on the client!");
            return false;
        }
        return true;
    }
}
