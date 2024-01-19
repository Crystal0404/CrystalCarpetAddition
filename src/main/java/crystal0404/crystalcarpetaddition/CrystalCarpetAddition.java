package crystal0404.crystalcarpetaddition;

import crystal0404.crystalcarpetaddition.config.Regconfig;
import crystal0404.crystalcarpetaddition.events.JoinedTheGame;
import crystal0404.crystalcarpetaddition.network.CCANetwork;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;
//#if MC>=12002
//$$ import net.fabricmc.fabric.api.networking.v1.ServerConfigurationConnectionEvents;
//#else
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
//#endif



public class CrystalCarpetAddition implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(CCAReference.getModName());
    @Override
    public void onInitialize() {
        Regconfig.reg_config();
        WrappedSettingManager.register(
                CCAReference.getModIdentifier(),
                CCAExtension.getSettingsManager(),
                new CCAExtension()
        );
        //#if MC>=12002
        //$$ ServerConfigurationConnectionEvents.CONFIGURE.register(new JoinedTheGame());
        //#else
        ServerPlayConnectionEvents.JOIN.register(new JoinedTheGame());
        //#endif
        CCANetwork.registerC2SPacks();
    }
}
