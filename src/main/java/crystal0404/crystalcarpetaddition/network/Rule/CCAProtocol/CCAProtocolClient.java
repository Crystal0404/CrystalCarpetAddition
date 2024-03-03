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
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collection;

public class CCAProtocolClient {
    private static String SEND;
    public static void checkMod(
            MinecraftClient client,
            ClientPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ){
        Gson gson = new Gson();
        String info = buf.readString();
        Collection<String> blackList = gson.fromJson(info, S2CSendModList.class).getBlackList();
        boolean regex = gson.fromJson(info, S2CSendModList.class).isRegex();
        boolean send = gson.fromJson(info, S2CSendModList.class).isSend();

        // Get a list of mods and convert them to Strings
        Collection<String> allMod = new ArrayList<>();
        FabricLoader.getInstance().getAllMods().forEach(mod -> allMod.add(mod.toString()));

        // Send the list of mods to the server
        if (send){
            Gson sendMod = new GsonBuilder().setPrettyPrinting().create();
            SEND = sendMod.toJson(new C2SSendModList(allMod));
        }

        // Check if there are any mods that are not allowed, the other is the regular expression pattern, the default is that the id must match exactly
        if (!regex){
            for (String s : allMod) {
                String mod = s.substring(0, s.indexOf(" "));
                if (blackList.contains(mod)){
                    haveBlackListMod(client, mod);
                    break;
                }
            }
        }else {
            for (String s : allMod) {
                boolean breakThis = false;
                for (String b : blackList) {
                    if (s.matches(b)){
                        breakThis = true;
                        haveBlackListMod(client, s.substring(0, s.indexOf(" ")));
                        break;
                    }
                }
                if (breakThis){
                    break;
                }
            }
        }
    }

    // disconnect
    private static void disconnect(MinecraftClient client, Text title, Text reason){
        client.execute(() -> {
            if (client.world != null){
                client.world.disconnect();
            }
            client.disconnect();
            client.setScreen(new DisconnectedScreen(new MultiplayerScreen(new TitleScreen()), title, reason));
        });
    }

    // The blacklisted module was found to be disconnected
    private static void haveBlackListMod(MinecraftClient client, String mod){
        Text title = Text.literal("CrystalCarpetAddition").setStyle(Style.EMPTY.withColor(0x55FFFF).withBold(true));
        Text reason = Text.literal("You can't use ").setStyle(Style.EMPTY.withColor(0x55FF55))
                .append(Text.literal(mod).setStyle(Style.EMPTY.withColor(0xFF5555).withUnderline(true)))
                .append(Text.literal(" in this server!").setStyle(Style.EMPTY.withColor(0x55FF55)));
        disconnect(client, title, reason);
    }

    // Sending packets to the server cannot be sent until the client's join event is loaded, which is registered in ClientPlayConnectionEventsJoin
    public static void send(){
        if (SEND != null && ClientPlayNetworking.canSend(CCANetwork.MOD)){
            ClientPlayNetworking.send(CCANetwork.MOD, PacketByteBufs.create().writeString(SEND));
        }
        SEND = null;
    }
}


