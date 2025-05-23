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

package com.github.crystal0404.mods.crystalcarpetaddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Translations;

import java.util.Map;

public class CCAExtension implements CarpetExtension {
    public static final SettingsManager CCASettingsManager = new SettingsManager(
            CrystalCarpetAdditionMod.version,
            CrystalCarpetAdditionMod.MOD_ID,
            CrystalCarpetAdditionMod.MOD_NAME
    );
    private static final CarpetExtension INSTANCE = new CCAExtension();

    public static void init() {
        CarpetServer.manageExtension(INSTANCE);
    }

    @Override
    public void onGameStarted() {
        CCASettingsManager.parseSettingsClass(CCASettings.class);
    }

    @Override
    public String version() {
        return CrystalCarpetAdditionMod.version;
    }

    @Override
    public SettingsManager extensionSettingsManager() {
        return CCASettingsManager;
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return Translations.getTranslationFromResourcePath("assets/cca/lang/%s.json".formatted(lang));
    }
}
