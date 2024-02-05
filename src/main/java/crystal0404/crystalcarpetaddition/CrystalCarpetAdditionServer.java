package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.events.ServerPlayConnectionEventsJoin;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class CrystalCarpetAdditionServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        CCANetwork.registerC2S();
        ServerPlayConnectionEvents.JOIN.register(new ServerPlayConnectionEventsJoin());
    }
}