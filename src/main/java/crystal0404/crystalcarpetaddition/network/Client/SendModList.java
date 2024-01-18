package crystal0404.crystalcarpetaddition.network.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.CrystalCarpetAdditionClient;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

import java.util.Collection;

public class SendModList {
    // 获取并发送模组列表
    public static void send_mod_list(MinecraftClient client, ClientPlayNetworkHandler handler,
                                     PacketByteBuf buf, PacketSender sender){
        Collection<ModContainer> get_mod = FabricLoader.getInstance().getAllMods();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        buf = PacketByteBufs.create().writeString(gson.toJson(get_mod));
        if (!ClientPlayNetworking.canSend(CCANetwork.MOD_LIST)){
            CrystalCarpetAdditionClient.LOGGER.warn("The packet sent failed, please try to connect to the server again!");
        }
        ClientPlayNetworking.send(CCANetwork.MOD_LIST, buf);
    }
}
