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

package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol.CCAProtocolClient;
import crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol.CCAProtocolServer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class CCANetwork {
    public static final Identifier HELLO = new Identifier(CCAReference.getModIdentifierCurrent(), "hello");
    public static final Identifier MOD = new Identifier(CCAReference.getModIdentifierCurrent(), "mod");
    public static void registerS2C(){
        ClientPlayNetworking.registerGlobalReceiver(HELLO, CCAProtocolClient::checkMod);
    }
    public static void registerC2S(){
        ServerPlayNetworking.registerGlobalReceiver(MOD, CCAProtocolServer::getMod);
    }
}
