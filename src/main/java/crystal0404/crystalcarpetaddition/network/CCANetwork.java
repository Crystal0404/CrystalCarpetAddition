package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.network.Client.CCAProtocol.CheckMod;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.util.Identifier;

public class CCANetwork {
    public static final Identifier HELLO = new Identifier(CCAReference.getModIdentifier(), "hello");
    public static void registerS2C(){
        ClientPlayNetworking.registerGlobalReceiver(HELLO, CheckMod::check_mod);
    }
}
