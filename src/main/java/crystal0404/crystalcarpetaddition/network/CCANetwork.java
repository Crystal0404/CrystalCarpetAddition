package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.network.Client.SendModList;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class CCANetwork {
    public static Identifier HELLO = new Identifier(CCAReference.getModIdentifier(), "hello");
    public static Identifier MOD_LIST = new Identifier(CCAReference.getModIdentifier(), "mod_list");
    public static void registerS2CPacks(){
        ClientPlayNetworking.registerGlobalReceiver(HELLO, SendModList::send_mod_list);
    }
    public static void registerC2SPacks(){

    }
}
