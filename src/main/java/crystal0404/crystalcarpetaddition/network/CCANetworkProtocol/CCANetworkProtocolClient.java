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

import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import crystal0404.crystalcarpetaddition.utils.FabricVersionChecker;
import crystal0404.crystalcarpetaddition.utils.Message.MessagePresets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Stop, this is not the place you should be : (
 */
public class CCANetworkProtocolClient {

    private static final Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;

    public static void client() {
        ClientPlayNetworking.registerGlobalReceiver(HELLO.ID, ((payload, context) -> {
            String info = payload.s();
            MinecraftClient client = context.client();
            if (CCASettings.CCADebug) LOGGER.debug("buf: \"{}\"", info);
            Gson gson = new Gson();
            HashMap<String, String> blackMap = gson.fromJson(info, SendBlackMod.class).getBlackModMap();

            // Determine if there are any mods that are not allowed, and disconnect them
            for (Map.Entry<String, String> stringStringEntry : blackMap.entrySet()) {
                boolean canBreak = false;
                for (ModContainer allMod : FabricLoader.getInstance().getAllMods()) {
                    String modId = allMod.getMetadata().getId();
                    if (
                            modId.matches(stringStringEntry.getKey())
                                    && FabricVersionChecker.isLoad(modId, stringStringEntry.getValue())
                    ) {
                        canBreak = true;
                        disconnect(
                                client,
                                MessagePresets.BLACKMODREASON(allMod.getMetadata().getName())
                        );
                        break;
                    }
                }
                if (canBreak) {
                    break;
                }
            }
        }));
    }

    private static void disconnect(@NotNull MinecraftClient client, Text reason) {
        client.execute(() -> {
            if (client.world != null) {
                client.world.disconnect();
            }
            client.disconnect();
            client.setScreen(new DisconnectedScreen(new MultiplayerScreen(new TitleScreen()), MessagePresets.CCATITLE, reason));
        });
    }

    // Send the client mod information to the server
    public static void clientPlayerJoin(MinecraftClient client) {
        if (!ClientPlayNetworking.canSend(CCANetworkProtocolServer.MOD.ID)) return;
        if (client.player == null) return;
        Gson gson = new Gson();
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        HashMap<String, String> modMap = new HashMap<>();
        FabricLoader.getInstance().getAllMods().forEach(mod ->
                modMap.put(mod.getMetadata().getId(), mod.getMetadata().getVersion().getFriendlyString())
        );
        map.put(client.player.getName().getString(), modMap);
        String send = gson.toJson(new ClientModList(map));
        ClientPlayNetworking.send(new CCANetworkProtocolServer.MOD(send));
    }

    public record HELLO(String s) implements CustomPayload {
        //#if MC > 12006
        public static final CustomPayload.Id<HELLO> ID = new Id<>(Identifier.of(CCANetwork.PROTOCOL, "hello"));
        //#else
        //$$ public static final CustomPayload.Id<HELLO> ID = new Id<>(new Identifier(CCANetwork.PROTOCOL, "hello"));
        //#endif
        public static final PacketCodec<RegistryByteBuf, HELLO> CODEC = PacketCodecs.STRING.xmap(HELLO::new, HELLO::s).cast();

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
