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

package crystal0404.crystalcarpetaddition;

import com.mojang.brigadier.CommandDispatcher;
import lombok.Getter;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import top.hendrixshen.magiclib.carpet.api.CarpetExtensionCompatApi;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class CCAExtension implements CarpetExtensionCompatApi {
    @Getter
    private static final CCASettingManager settingsManager = new CCASettingManager(
            CCAReference.getModVersion(),
            CCAReference.getModIdentifier(),
            CCAReference.getModNameCurrent()
    );
    @Getter
    private static MinecraftServer server;

    @Override
    public void onGameStarted() {
        CCAExtension.settingsManager.parseSettingsClass(CCASettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        CCAExtension.server = server;
    }

    @Override
    public WrappedSettingManager getSettingsManagerCompat() {
        return CCAExtension.settingsManager;
    }

    @Override
    public void registerCommandCompat(CommandDispatcher<ServerCommandSource> dispatcher) {

    }

}
