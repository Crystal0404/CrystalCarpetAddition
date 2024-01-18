package crystal0404.crystalcarpetaddition.mixin.Rule.CCANetworkProtocol;

import crystal0404.crystalcarpetaddition.CCASettings;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerManager.class)
public abstract class CCANetworkProtocolMixin {
    @Shadow @Final private MinecraftServer server;

    @Inject(method = "onPlayerConnect", at = @At(value = "TAIL"))
    private void player_manger_mixin(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci){
        if (CCASettings.CCANetworkProtocol){
            // 判断是否是carpet假人
            if (connection.getAddress() != null){
                // 发一个空包
                if (ServerPlayNetworking.canSend(player, CCANetwork.HELLO)){
                    ServerPlayNetworking.send(player, CCANetwork.HELLO, PacketByteBufs.create());
                }else {
                    ServerPlayNetworkHandler dis = new ServerPlayNetworkHandler(server, connection, player);
                    // 断开连接以及一些字体样式
                    dis.disconnect(Text.literal("Please install CrystalCarpetAddition!\n")
                            .setStyle(Style.EMPTY.withColor(TextColor.parse("aqua")))
                            .append(Text.literal("https://modrinth.com/mod/crystalcarpetaddition")
                                    .setStyle(Style.EMPTY.withColor(TextColor.parse("green"))
                                            .withUnderline(true))));
                }
            }
        }
    }
}
