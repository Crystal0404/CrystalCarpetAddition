package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.network.Client.SendModList;
import crystal0404.crystalcarpetaddition.network.Server.CheckModList;
//#if MC < 12002
//$$ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//$$ import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//#else
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
//#endif
import net.minecraft.util.Identifier;

public class CCANetwork {
    public static Identifier HELLO = new Identifier(CCAReference.getModIdentifier(), "hello");
    public static Identifier MOD_LIST = new Identifier(CCAReference.getModIdentifier(), "mod_list");
    public static void registerS2CPacks(){
        //#if MC < 12002
        //$$ ClientPlayNetworking.registerGlobalReceiver(HELLO, SendModList::send_mod_list);
        //#else
        ClientConfigurationNetworking.registerGlobalReceiver(HELLO, SendModList::send_mod_list);
        //#endif

    }
    public static void registerC2SPacks(){
        //#if MC < 12002
        //$$ ServerPlayNetworking.registerGlobalReceiver(MOD_LIST, CheckModList::check_mod_list);
        //#else
        ServerConfigurationNetworking.registerGlobalReceiver(MOD_LIST, CheckModList::check_mod_list);
        //#endif
    }
}
