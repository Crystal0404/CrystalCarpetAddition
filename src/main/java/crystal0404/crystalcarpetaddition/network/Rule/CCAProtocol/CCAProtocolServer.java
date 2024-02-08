package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import com.google.gson.Gson;
import crystal0404.crystalcarpetaddition.CCAReference;
import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.config.ReadConfig;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class CCAProtocolServer {
    // 玩家进入游戏时调用(在1.20.2以下经过群组服时调用这个会有问题)
    public static void playerJoinGame(ServerPlayNetworkHandler handler){

        // 没开启相关设置, 跳过执行
        if (!CCASettings.CCAProtocol){return;}

        // 不是真玩家, 跳过执行
        if (handler.player.toString().contains("EntityPlayerMPFake")){return;}

        // 判断是否可以发送数据包, 不能发送就踢出服务器
        if (!ServerPlayNetworking.canSend(handler.player, CCANetwork.HELLO)){
            CCAReference.getLogger().info("The packet failed to be sent and the player may not have CCA installed");
            handler.disconnect(Text.literal("\nPlease install CrystalCarpetAddition!\n")
                    .setStyle(Style.EMPTY.withColor(0x55FFFF))
                    .append(Text.literal("https://modrinth.com/mod/crystalcarpetaddition")
                            .setStyle(Style.EMPTY.withColor(0x55FF55).withUnderline(true))));
            return;
        }
        Gson gson = new Gson();
        String blackMod = gson.toJson(new S2CSendModList(ReadConfig.MOD_BLACK_LIST, ReadConfig.GET_MOD, ReadConfig.REGEX));
        PacketByteBuf buf = PacketByteBufs.create().writeString(blackMod);
        ServerPlayNetworking.send(handler.player, CCANetwork.HELLO, buf);
    }

    // 接收客户端返回的数据包
    public static void getMod(
            MinecraftServer server,
            ServerPlayerEntity player,
            ServerPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ){
        String info = buf.readString();
        CCAReference.getLogger().info(info);
    }
}
