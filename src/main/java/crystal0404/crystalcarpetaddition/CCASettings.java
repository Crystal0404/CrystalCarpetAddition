package crystal0404.crystalcarpetaddition;

import top.hendrixshen.magiclib.carpet.api.annotation.Rule;

public class CCASettings {
    private static final String CCA = "CrystalCarpetAddition";
    private static final String NETWORK = "Network";
    @Rule(categories = CCA)
    //#if MC >=12002
    public static boolean MagicBox = false;
    //#else
    //$$ public static boolean MagicBox = true;
    //#endif

    //#if MC < 12004
    //$$ @Rule(categories = CCA)
    //$$ public static boolean CEnderPearlChunkLoading = false;
    //#endif

    @Rule(categories = CCA)
    public static boolean ItemShadowing = false;
    @Rule(categories = NETWORK)
    public static boolean CCAProtocol = false;
    //#if MC >= 12004
    @Rule(categories = CCA)
    public static boolean ComparatorCanPlaceAboveAir = false;
    //#endif
}
