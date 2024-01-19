package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.CCAReference;
import net.minecraft.util.Identifier;
import crystal0404.crystalcarpetaddition.network.Client.SendModList;
import crystal0404.crystalcarpetaddition.network.Server.CheckModList;
//#if MC >= 12002
//$$ import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
//$$ import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
//#else
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//#endif

public class CCANetwork {
    public static Identifier HELLO = new Identifier(CCAReference.getModIdentifier(), "hello");
    public static Identifier MOD_LIST = new Identifier(CCAReference.getModIdentifier(), "mod_list");
    public static void registerS2CPacks(){
        //#if MC >= 12002
        //$$ ClientConfigurationNetworking.registerGlobalReceiver(HELLO, SendModList::send_mod_list);
        //#else
        ClientPlayNetworking.registerGlobalReceiver(HELLO, SendModList::send_mod);
        //#endif
    }
    public static void registerC2SPacks(){
        //#if MC >= 12002
        //$$ ServerConfigurationNetworking.registerGlobalReceiver(MOD_LIST, CheckModList::check_mod_list);
        //#else
        ServerPlayNetworking.registerGlobalReceiver(MOD_LIST, CheckModList::check_mod);
        //#endif
    }
}
