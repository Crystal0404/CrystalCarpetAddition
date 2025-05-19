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
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.util.Objects;

/**
 * Some tools
 */
@SuppressWarnings("unused")
public final class CCAUtils {
    /**
     * This is all the hidden parameters
     */
    private final static ImmutableMap<String, Key> JAVA_PARAMETERS = new ImmutableMap.Builder<String, Key>()
            .put("cca.disable.EasterEggs", new Key(!isFalse("cca.disable.EasterEggs"), true))
            .put("cca.enable.debug", new Key(Boolean.getBoolean("cca.enable.debug"), false))
            .put("cca.enable.MagicSettings", new Key(Boolean.getBoolean("cca.enable.MagicSettings"), false))
            .buildOrThrow();

    private record Key(@NotNull Boolean value, @NotNull Boolean defaultValue) {

    }

    static {
        JAVA_PARAMETERS.forEach((k, v) -> {
            if (v.value() != v.defaultValue()) {
                CrystalCarpetAdditionMod.LOGGER.warn("[CCA] -D{}={}", k, v.value());
            }
        });
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

    @SuppressWarnings("all")
    private static boolean isFalse(String name) {
        boolean result = false;
        try {
            result = "false".equalsIgnoreCase(System.getProperty(name));
        } catch (IllegalArgumentException | NullPointerException ignored) {

        }
        return result;
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
    @Deprecated
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
        return Objects.requireNonNull(JAVA_PARAMETERS.get("cca.enable.debug")).value();
    }

    public final static class DisableEasterEggs implements ConditionTester {
        @Override
        public boolean isSatisfied(String mixinClassName) {
            return Objects.requireNonNull(JAVA_PARAMETERS.get("cca.disable.EasterEggs")).value();
        }
    }

    public final static class EnableMagicSetting implements ConditionTester, Rule.Condition {
        @Override
        public boolean isSatisfied(String mixinClassName) {
            return Objects.requireNonNull(JAVA_PARAMETERS.get("cca.enable.MagicSettings")).value();
        }

        @Override
        public boolean shouldRegister() {
            return Objects.requireNonNull(JAVA_PARAMETERS.get("cca.enable.MagicSettings")).value();
        }
    }
}
