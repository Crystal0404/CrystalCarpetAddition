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

package crystal0404.crystalcarpetaddition.utils.mixin;

import crystal0404.crystalcarpetaddition.CCAReference;
import me.fallenbreath.conditionalmixin.api.mixin.RestrictiveMixinConfigPlugin;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class CCAMixinPluginConfig extends RestrictiveMixinConfigPlugin {

    private static final Logger LOGGER = CCAReference.getLogger();
    @Override
    protected void onRestrictionCheckFailed(String mixinClassName, String reason){
//        LOGGER.error(mixinClassName);
//        LOGGER.error(reason);
        // This interface is not perfect, it is a temporary code, and the log output may be incorrect
        if (!reason.matches(String.format(".*%s.*", mixinClassName))) {
            LOGGER.warn("[CCA] \"{}\" is disabled because of \"{}\"", mixinClassName, reason);
        }
    }
    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }
}
