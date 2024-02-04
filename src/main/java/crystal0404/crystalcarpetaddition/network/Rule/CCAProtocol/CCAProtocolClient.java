package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Collection;

public class CCAProtocolClient {
    private static String SEND;
    public static void checkMod(
            MinecraftClient client,
            ClientPlayNetworkHandler handler,
            PacketByteBuf buf,
            PacketSender sender
    ){
        Gson gson = new Gson();
        String info = buf.readString();
        Collection<String> blackList = gson.fromJson(info, S2CSendModList.class).getBlackList();
        boolean regex = gson.fromJson(info, S2CSendModList.class).isRegex();
        boolean send = gson.fromJson(info, S2CSendModList.class).isSend();
        int version = gson.fromJson(info, S2CSendModList.class).getVersion();

        // 检查版本
        if (version != new S2CSendModList().getVersion()){
            Text title = Text.literal("CrystalCarpetAddition").setStyle(Style.EMPTY.withColor(0x55FFFF).withBold(true));
            Text reason = Text.literal("The CCA protocol version is incorrect, please try to change the CrystalCarpetAddition.")
                    .setStyle(Style.EMPTY.withColor(0xFF5555))
                    .append(Text.literal("\nhttps://modrinth.com/mod/crystalcarpetaddition")
                            .setStyle(Style.EMPTY.withColor(0x55FF55).withUnderline(true)));
            disconnect(client, title, reason);
            return;
        }

        // 获取模组列表, 并转化成String
        Collection<String> allMod = new ArrayList<>();
        FabricLoader.getInstance().getAllMods().forEach(mod -> allMod.add(mod.toString()));

        // 把模组列表发送给服务器
        if (send){
            Gson sendMod = new GsonBuilder().setPrettyPrinting().create();
            SEND = sendMod.toJson(new C2SSendModList(allMod));
        }

        // 检查是否有不允许的模组(正则表达式咕咕咕咕咕...)
        if (true){
            for (String s : allMod) {
                String mod = s.substring(0, allMod.toString().indexOf(" "));
                if (blackList.contains(mod)){
                    Text title = Text.literal("CrystalCarpetAddition").setStyle(Style.EMPTY.withColor(0x55FFFF).withBold(true));
                    Text reason = Text.literal("You can't use ").setStyle(Style.EMPTY.withColor(0x55FF55))
                            .append(Text.literal(mod).setStyle(Style.EMPTY.withColor(0xFF5555).withUnderline(true)))
                            .append(Text.literal(" in this server!").setStyle(Style.EMPTY.withColor(0x55FF55)));
                    disconnect(client, title, reason);
                    break;
                }
            }
        }
    }

    // 断开连接
    private static void disconnect(MinecraftClient client, Text title, Text reason){
        client.execute(() -> {
            if (client.world != null){
                client.world.disconnect();
            }
            client.disconnect();
            client.setScreen(new DisconnectedScreen(new MultiplayerScreen(new TitleScreen()), title, reason));
        });
    }

    // 向服务器发送数据包要等到客户端的join事件完成加载, 不然是不能发送的, 这个函数注册在ClientPlayConnectionEventsJoin
    public static void send(){
        if (SEND != null && ClientPlayNetworking.canSend(CCANetwork.MOD)){
            ClientPlayNetworking.send(CCANetwork.MOD, PacketByteBufs.create().writeString(SEND));
        }
        SEND = null;
    }
}


