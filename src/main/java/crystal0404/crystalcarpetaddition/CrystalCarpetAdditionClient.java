package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.ClientModInitializer;
//#if MC < 12002
import crystal0404.crystalcarpetaddition.events.ClientJoinedTheGame;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
//#endif
public class CrystalCarpetAdditionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //#if MC < 12002
        ClientPlayConnectionEvents.JOIN.register(new ClientJoinedTheGame());
        //#endif
        CCANetwork.registerS2CPacks();
    }
}
