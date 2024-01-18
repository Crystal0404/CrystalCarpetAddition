package crystal0404.crystalcarpetaddition.network.other;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
// 没有实际用处, 只是方便正反序列化
@Getter
public class ModList {
    @Setter
    private Collection<String> mod_list;

    public ModList(Collection<String> mod_list) {
        this.mod_list = mod_list;
    }

    @Override
    public String toString() {
        return "ModList{" +
                "mod_list=" + mod_list +
                '}';
    }
}
