package crystal0404.crystalcarpetaddition;

import com.mojang.brigadier.CommandDispatcher;
import lombok.Getter;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import top.hendrixshen.magiclib.carpet.api.CarpetExtensionCompatApi;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class CCAExtension implements CarpetExtensionCompatApi {
    @Getter
    private static final CCASettingManager settingsManager = new CCASettingManager(
            CCAReference.getModVersion(),
            CCAReference.getModIdentifier(),
            CCAReference.getModNameCurrent()
    );
    @Getter
    private static MinecraftServer server;

    @Override
    public void onGameStarted() {
        CCAExtension.settingsManager.parseSettingsClass(CCASettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        CCAExtension.server = server;
    }

    @Override
    public WrappedSettingManager getSettingsManagerCompat() {
        return CCAExtension.settingsManager;
    }

    @Override
    public void registerCommandCompat(CommandDispatcher<ServerCommandSource> dispatcher) {

    }

}
