package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
class C2SSendModList {
    @Setter
    private Collection<String> ModList;

    public C2SSendModList(Collection<String> modList) {
        ModList = modList;
    }

    @Override
    public String toString() {
        return "C2SSendModList{" +
                "ModList=" + ModList +
                '}';
    }
}
