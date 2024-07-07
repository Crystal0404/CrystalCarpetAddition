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

package crystal0404.crystalcarpetaddition.utils;

import carpet.api.settings.Rule;
import com.google.common.collect.ImmutableMap;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import me.fallenbreath.conditionalmixin.api.mixin.ConditionTester;
import me.fallenbreath.conditionalmixin.api.util.VersionChecker;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;

/**
 * Some tools
 */
@SuppressWarnings("unused")
public final class CCAUtils {
    /**
     * This is all the hidden parameters
     */
    private final static ImmutableMap<String, Boolean> JAVA_PARAMETERS = new ImmutableMap.Builder<String, Boolean>()
            .put("cca.disable.EasterEggs", Boolean.getBoolean("cca.disable.EasterEggs"))
            .put("cca.enable.debug", Boolean.getBoolean("cca.enable.debug"))
            .put("cca.enable.MagicSettings", Boolean.getBoolean("cca.enable.MagicSettings"))
            .put("cca.enable.network.debug", Boolean.getBoolean("cca.enable.network.debug"))
            .buildOrThrow();

    static {
        JAVA_PARAMETERS.forEach((k, v) -> {if (v) CrystalCarpetAdditionMod.LOGGER.warn("[CCA] -D{}=true", k);});
    }

    /**
     * Find if a class exists
     */
    public static boolean tryFindClass(String className) {
        try {
            MixinService.getService().getBytecodeProvider().getClassNode(className);
            return true;
        } catch (ClassNotFoundException classNotFoundException) {
            return false;
        } catch (IOException ioException) {
            CrystalCarpetAdditionMod.LOGGER.error("[CCA] An unknown exception occurred while trying to find the class");
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Check whether the modId satisfies the version Predicate
     */
    public static boolean isLoad(String modId, String versionPredicate) {
        return VersionChecker.doesModVersionSatisfyPredicate(modId, versionPredicate);
    }

    /**
     * This is for my own use
     * Others please use the one above
     */
    public static boolean isLoad(String ruleName, String modId, String versionPredicate) {
        if (VersionChecker.doesModVersionSatisfyPredicate(modId, versionPredicate)) {
            return true;
        }
        CrystalCarpetAdditionMod.LOGGER.warn(
                "[CCA] Rule \"{}\" is disabled, Because \"{}\" does not meet condition \"{}\"",
                ruleName,
                modId,
                versionPredicate
        );
        return false;
    }

    public static boolean isEnableDebug() {
        return Boolean.TRUE.equals(JAVA_PARAMETERS.get("cca.enable.debug"));
    }

    public static boolean isEnableNetworkDebug() {
        return Boolean.TRUE.equals(JAVA_PARAMETERS.get("cca.enable.network.debug"));
    }

    public final static class DisableEasterEggs implements ConditionTester {
        @Override
        public boolean isSatisfied(String mixinClassName) {
            return !Boolean.TRUE.equals(JAVA_PARAMETERS.get("cca.disable.EasterEggs"));
        }
    }

    public final static class EnableMagicSetting implements ConditionTester, Rule.Condition {
        @Override
        public boolean isSatisfied(String mixinClassName) {
            return Boolean.TRUE.equals(JAVA_PARAMETERS.get("cca.enable.MagicSettings"));
        }

        @Override
        public boolean shouldRegister() {
            return Boolean.TRUE.equals(JAVA_PARAMETERS.get("cca.enable.MagicSettings"));
        }
    }
}
