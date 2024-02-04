package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.events.ClientPlayConnectionEventsJoin;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class CrystalCarpetAdditionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register(new ClientPlayConnectionEventsJoin());
        CCANetwork.registerS2C();
    }
}
