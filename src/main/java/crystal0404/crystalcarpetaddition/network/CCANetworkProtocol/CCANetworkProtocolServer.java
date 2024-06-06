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
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Stop, this is not the place you should be : (
 */
public class CCANetworkProtocolServer {
    private static final Logger LOGGER = CrystalCarpetAdditionMod.LOGGER;
    private static final Set<GetClientModMap> CALL = new HashSet<>();

    public static void register(GetClientModMap getClientModMap) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) LOGGER.info("\"{}\" register", getClientModMap.toString());
        CALL.add(getClientModMap);
    }

    public static void playerJoinGame(ServerPlayNetworkHandler handler) {
        if (!CCASettings.CCANetworkProtocol) return;

        if (handler.getPlayer() instanceof EntityPlayerMPFake) return;

        if (!ServerPlayNetworking.canSend(handler.getPlayer(), CCANetworkProtocolClient.HELLO.ID)) {
            LOGGER.info("[CCA] The packet failed to be sent and the player may not have CCA installed");
            if (ReadConfig.isCanKick()) handler.disconnect(MessagePresets.INSTALLATION);
            return;
        }
        Gson gson = new Gson();
        String send = gson.toJson(new SendBlackMod(ReadConfig.getBlackList()));
        if (send.length() > 32767) {
            LOGGER.error("[CCA] The blacklist is too long");
            throw new RuntimeException("[CCA] The blacklist is too long");
        }
        ServerPlayNetworking.send(handler.getPlayer(), new CCANetworkProtocolClient.HELLO(send));
    }

    public static void server() {
        ServerPlayNetworking.registerGlobalReceiver(MOD.ID, ((payload, context) -> {
            if (!CCASettings.CCANetworkProtocol) return;
            String info = payload.data();
            if (CCASettings.CCADebug) LOGGER.info("buf: \"{}\"", info);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ClientModList clientModList = gson.fromJson(info, ClientModList.class);
            if (ReadConfig.isCanPrintMod()) LOGGER.info(gson.toJson(clientModList));
            MinecraftServer server = context.player().getServer();
            ServerPlayerEntity player = context.player();
            ServerPlayNetworkHandler handler = context.player().networkHandler;
            CALL.forEach(c -> c.getMod(server, player, handler, clientModList.getClientModMap().get(player.getName().getString())));
        }));
    }

    public record MOD(String data) implements CustomPayload {
        //#if MC > 12006
        public static final CustomPayload.Id<MOD> ID = new Id<>(Identifier.of(CCANetwork.PROTOCOL, "mod"));
        //#else
        //$$ public static final CustomPayload.Id<MOD> ID = new Id<>(new Identifier(CCANetwork.PROTOCOL, "mod"));
        //#endif
        public static final PacketCodec<RegistryByteBuf, MOD> CODEC = PacketCodecs.STRING.xmap(MOD::new, MOD::data).cast();

        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
