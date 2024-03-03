package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
class C2SSendModList {
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
