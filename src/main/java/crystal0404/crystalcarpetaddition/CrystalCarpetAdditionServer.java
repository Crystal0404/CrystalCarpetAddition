package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.events.ServerPlayConnectionEventsJoin;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class CrystalCarpetAdditionServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ServerPlayConnectionEvents.JOIN.register(new ServerPlayConnectionEventsJoin());
    }
}
