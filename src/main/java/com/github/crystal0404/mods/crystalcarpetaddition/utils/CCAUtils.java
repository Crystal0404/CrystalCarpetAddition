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

package com.github.crystal0404.mods.crystalcarpetaddition.utils;

import com.github.crystal0404.mods.crystalcarpetaddition.CrystalCarpetAdditionMod;
import com.google.common.collect.ImmutableMap;
import me.fallenbreath.conditionalmixin.api.util.VersionChecker;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Some tools
 */
@SuppressWarnings("unused")
public final class CCAUtils {
    /**
     * This is all the hidden parameters
     */
    private final static ImmutableMap<@NotNull String, @NotNull Key> JAVA_PARAMETERS = new ImmutableMap.Builder<@NotNull String, @NotNull Key>()
            .put("cca.enable.debug", new Key(Boolean.getBoolean("cca.enable.debug"), false))
            .buildOrThrow();

    static {
        JAVA_PARAMETERS.forEach((k, v) -> {
            if (v.value() != v.defaultValue()) {
                CrystalCarpetAdditionMod.LOGGER.warn("[CCA] -D{}={}", k, v.value());
            }
        });
    }

    /**
     * Check whether the modId satisfies the version Predicate
     */
    public static boolean isLoad(String modId, String versionPredicate) {
        return VersionChecker.doesModVersionSatisfyPredicate(modId, versionPredicate);
    }

    public static boolean isDisableDebug() {
        return !Objects.requireNonNull(JAVA_PARAMETERS.get("cca.enable.debug")).value();
    }

    private record Key(boolean value, boolean defaultValue) {
    }
}
