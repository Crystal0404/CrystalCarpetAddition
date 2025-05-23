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

package com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.impl;

import com.github.crystal0404.mods.crystalcarpetaddition.CrystalCarpetAdditionMod;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.CCAUtils;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.Condition;
import com.github.crystal0404.mods.crystalcarpetaddition.utils.annotation.Restriction;

public abstract class AnnotationProcessor {
    public static boolean shouldRegister(Restriction restriction, String rule) {
        for (Condition condition : restriction.conflict()) {
            if (CCAUtils.isLoad(condition.value(), condition.versionPredicates())) {
                conflictInfo(rule, condition);
                return false;
            }
        }

        for (Condition condition : restriction.require()) {
            if (!CCAUtils.isLoad(condition.value(), condition.versionPredicates())) {
                requireInfo(rule, condition);
                return false;
            }
        }

        return true;
    }

    private static void conflictInfo(String rule, Condition condition) {
        if (!CCAUtils.isEnableDebug()) return;
        CrystalCarpetAdditionMod.LOGGER.info(
                "[CCA] Rule {} is disabled, Because of the conflict with {}({}).",
                rule,
                condition.value(),
                condition.versionPredicates()
        );
    }

    private static void requireInfo(String rule, Condition condition) {
        if (!CCAUtils.isEnableDebug()) return;
        CrystalCarpetAdditionMod.LOGGER.info(
                "[CCA] Rule {} is disabled, Because of the require with {}({}).",
                rule,
                condition.value(),
                condition.versionPredicates()
        );
    }
}
