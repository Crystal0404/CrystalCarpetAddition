package crystal0404.crystalcarpetaddition.network.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.CrystalCarpetAddition;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import crystal0404.crystalcarpetaddition.network.other.ModList;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
//#if MC >= 12002
import net.minecraft.client.network.ClientConfigurationNetworkHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
//#else
//$$ import net.minecraft.client.network.ClientPlayNetworkHandler;
//$$ import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//#endif
import net.minecraft.network.PacketByteBuf;

import java.util.ArrayList;
import java.util.Collection;

public class SendModList {
    // 获取并发送模组列表
    public static void send_mod_list(MinecraftClient client,
                                     //#if MC>= 12002
                                     ClientConfigurationNetworkHandler handler,
                                     //#else
                                     //$$ ClientPlayNetworkHandler handler,
                                     //#endif
                                     PacketByteBuf buf, PacketSender sender){
        Collection<ModContainer> get = FabricLoader.getInstance().getAllMods();
        Collection<String> get_mod = new ArrayList<>();
        for (ModContainer modContainer : get) {
            get_mod.add(modContainer.toString().substring(0, modContainer.toString().indexOf(" ")));
        }
        ModList send_mod = new ModList(get_mod);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        buf = PacketByteBufs.create().writeString(gson.toJson(send_mod));
        if (
                //#if MC >= 12002
                !ClientConfigurationNetworking
                //#else
                //$$ !ClientPlayNetworking
                //#endif
                        .canSend(CCANetwork.MOD_LIST)){
            CrystalCarpetAddition.LOGGER.warn("The packet sent failed, please try to connect to the server again!");
        }
        //#if MC >= 12002
        ClientConfigurationNetworking.send(CCANetwork.MOD_LIST, buf);
        //#else
        //$$ ClientPlayNetworking.send(CCANetwork.MOD_LIST, buf);
        //#endif
    }
}
