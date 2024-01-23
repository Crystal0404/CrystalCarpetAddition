package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.ClientModInitializer;

public class CrystalCarpetAdditionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CCANetwork.registerS2C();
    }
}
