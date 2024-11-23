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

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import crystal0404.crystalcarpetaddition.utils.CCAUtils;
import crystal0404.crystalcarpetaddition.utils.Message.MessagePresets;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
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
import java.util.NoSuchElementException;

/**
 * Stop, this is not the place you should be : (
 */
@Deprecated(forRemoval = true)
@SuppressWarnings("removal")
public class CCANetworkProtocolClient {

    private static final Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;

    public static void client() {
        ClientPlayNetworking.registerGlobalReceiver(HELLO.ID, ((payload, context) -> {
            String info = payload.s();
            MinecraftClient client = context.client();
            if (CCAUtils.isEnableNetworkDebug()) LOGGER.info("buf: \"{}\"", info);
            Gson gson = new Gson();
            ImmutableList<String> blackPackages = ImmutableList.copyOf(gson.fromJson(info, SendBlackPackages.class).getBlackPackage());
            blackPackageChecker(client, blackPackages);
        }));
    }

    private static void blackPackageChecker(MinecraftClient client ,ImmutableList<String> blackPackages) {
        for (String blackPackage : blackPackages) {
            // example: cca$crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod -> crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod
            String className = blackPackage.replaceAll("^(.{1,63}\\$)", "");

            if (CCAUtils.tryFindClass(className)) {
                // example: cca$crystal0404.crystalcarpetaddition.CrystalCarpetAdditionMod -> cca
                String modId = blackPackage.replaceAll("\\$.+", "");

                String modName;
                try {
                    modName = FabricLoader.getInstance().getModContainer(modId).orElseThrow().getMetadata().getName();
                } catch (NoSuchElementException e) {
                    modName = "(Unknown mod)";
                }
                disconnect(client, MessagePresets.blackModResson(modName));
                return;
            }
        }
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
        FabricLoader.getInstance().getAllMods().forEach(mod -> {
            modMap.put(mod.getMetadata().getId(), mod.getMetadata().getVersion().getFriendlyString());

            // If the string is too long, send it in parts
            if (gson.toJson(modMap).length() > 32500) {
                LOGGER.warn("[CCA] The string is too long, and you try to send it in parts");
                map.put(client.player.getName().getString(), modMap);
                String send = gson.toJson(new ClientModList(map));
                ClientPlayNetworking.send(new CCANetworkProtocolServer.MOD(send));
                map.clear();
                modMap.clear();
            }
        });
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
