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

package crystal0404.crystalcarpetaddition.network.CCANetworkProtocol;

import carpet.patches.EntityPlayerMPFake;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import crystal0404.crystalcarpetaddition.api.CCANetorkProtocol.GetClientModMap;
import crystal0404.crystalcarpetaddition.config.ReadConfig;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import crystal0404.crystalcarpetaddition.utils.Message.MessagePresets;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;

import java.util.*;

/**
 * Stop, this is not the place you should be : (
 */
public class CCANetworkProtocolServer {
    private static final Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;
    private static final Set<GetClientModMap> CALL = new HashSet<>();
    public static void register(GetClientModMap getClientModMap) {
        if (CCASettings.CCADebug) LOGGER.debug("\"{}\" register", getClientModMap.toString());
        CALL.add(getClientModMap);
    }
    public static void playerJoinGame(ServerPlayNetworkHandler handler) {
        if (!CCASettings.CCANetworkProtocol) return;

        if (handler.getPlayer() instanceof EntityPlayerMPFake) return;

        if (!ServerPlayNetworking.canSend(handler.getPlayer(), CCANetwork.HELLO)) {
            LOGGER.info("The packet failed to be sent and the player may not have CCA installed");
            if (ReadConfig.CAN_KICK) handler.disconnect(MessagePresets.INSTALLATION);
            return;
        }
        Gson gson = new Gson();
        String send = gson.toJson(new SendBlackMod(ReadConfig.BLACKLIST));
        ServerPlayNetworking.send(handler.getPlayer(), CCANetwork.HELLO, PacketByteBufs.create().writeString(send));
    }

    // Received a list of mods from the client
    public static void server(
            MinecraftServer server,
            ServerPlayerEntity player,
            ServerPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ) {
        if (!CCASettings.CCANetworkProtocol) return;
        String info = buf.readString();
        if (CCASettings.CCADebug) LOGGER.debug("buf: \"{}\"", info);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ClientModList clientModList = gson.fromJson(info, ClientModList.class);
        if (ReadConfig.CAN_PRINT_MOD) LOGGER.info(gson.toJson(clientModList));
        CALL.forEach(c ->
            c.getMod(server, player, handler, clientModList.getClientModMap().get(player.getName().getString()))
        );
    }
}
