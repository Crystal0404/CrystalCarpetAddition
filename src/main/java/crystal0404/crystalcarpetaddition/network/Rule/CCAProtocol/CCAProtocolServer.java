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

package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.config.ReadConfig;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class CCAProtocolServer {
    // Called when the player enters the game (this will be problematic when passing through the group server below 1.20.2)
    public static void playerJoinGame(ServerPlayNetworkHandler handler){

        // If the relevant settings are not enabled, the execution is skipped
        if (!CCASettings.CCAProtocol){return;}

        // Not a real player, skip execution
        if (handler.player.toString().contains("EntityPlayerMPFake")){return;}

        // Determine whether the packet can be sent, and kick the server if it can't
        if (!ServerPlayNetworking.canSend(handler.player, CCANetwork.HELLO)){
            CCAReference.getLogger().info("The packet failed to be sent and the player may not have CCA installed");
            handler.disconnect(Text.literal("\nPlease install CrystalCarpetAddition!\n")
                    .setStyle(Style.EMPTY.withColor(0x55FFFF))
                    .append(Text.literal("https://modrinth.com/mod/crystalcarpetaddition")
                            .setStyle(Style.EMPTY.withColor(0x55FF55).withUnderline(true))));
            return;
        }
        Gson gson = new Gson();
        String blackMod = gson.toJson(new S2CSendModList(ReadConfig.MOD_BLACK_LIST, ReadConfig.GET_MOD, ReadConfig.REGEX));
        PacketByteBuf buf = PacketByteBufs.create().writeString(blackMod);
        ServerPlayNetworking.send(handler.player, CCANetwork.HELLO, buf);
    }

    // Receive the packet returned by the client
    public static void getMod(
            MinecraftServer server,
            ServerPlayerEntity player,
            ServerPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ){
        String info = buf.readString();
        CCAReference.getLogger().info(info);
    }
}
