package crystal0404.crystalcarpetaddition.network;

import crystal0404.crystalcarpetaddition.network.CCANetworkProtocol.CCANetworkProtocolClient;
import crystal0404.crystalcarpetaddition.network.CCANetworkProtocol.CCANetworkProtocolServer;
import crystal0404.crystalcarpetaddition.utils.FabricVersionChecker;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CCANetwork {
    private final static String PROTOCOL = "CCANetworkProtocol-v1.0";
    public final static Identifier HELLO = new Identifier(PROTOCOL, "hello");
    public final static Identifier MOD = new Identifier(PROTOCOL, "mod");

    public static void init() {
        if (!FabricVersionChecker.isLoad("fabric-api", "*")) return;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) registerS2C();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) registerC2S();
    }

    public static void registerS2C() {
        ClientPlayNetworking.registerGlobalReceiver(HELLO, CCANetworkProtocolClient::client);
    }

    public static void registerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(MOD, CCANetworkProtocolServer::server);
    }
}
