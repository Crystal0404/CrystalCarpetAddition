package crystal0404.crystalcarpetaddition;

import net.fabricmc.api.ModInitializer;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class CrystalCarpetAddition implements ModInitializer {
    @Override
    public void onInitialize() {
        WrappedSettingManager.register(
                CCAReference.getModIdentifier(),
                CCAExtension.getSettingsManager(),
                new CCAExtension()
        );
    }
}
