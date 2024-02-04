package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol.CCAProtocolClient;
import crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol.CCAProtocolServer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class CCANetwork {
    public static final Identifier HELLO = new Identifier(CCAReference.getModIdentifier(), "hello");
    public static final Identifier MOD = new Identifier(CCAReference.getModIdentifier(), "mod");
    public static void registerS2C(){
        ClientPlayNetworking.registerGlobalReceiver(HELLO, CCAProtocolClient::checkMod);
    }
    public static void registerC2S(){
        ServerPlayNetworking.registerGlobalReceiver(MOD, CCAProtocolServer::getMod);
    }
}
