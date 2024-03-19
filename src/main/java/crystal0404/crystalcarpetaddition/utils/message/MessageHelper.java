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

package crystal0404.crystalcarpetaddition.utils.message;

import crystal0404.crystalcarpetaddition.CCASettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import top.hendrixshen.magiclib.util.FabricUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageHelper {
    private final static List<Text> messageList = new ArrayList<>();

    private static boolean canAdd = true;

    public static void addMessage(Text text) {
        if (!CCASettings.KeepMessage) return;

        if (!canAdd) return;

        messageList.add(text);
        while (messageList.size() > 100) {
            messageList.remove(0);
        }
    }

    public static void clear() {
        messageList.clear();
    }

    public static void send(MinecraftClient client) {
        if (!notDisable()) {
            if (!messageList.isEmpty()) clear();
            return;
        }

        if (client.player == null) return;

        canAdd = false;
        messageList.forEach(text -> client.player.sendMessage(text));
        canAdd = true;
    }

    private static boolean notDisable() {
        return CCASettings.KeepMessage && FabricUtil.isModLoaded("minecraft", ">=1.20.4");
    }
}
