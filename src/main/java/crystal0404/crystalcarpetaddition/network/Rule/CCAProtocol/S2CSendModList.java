package crystal0404.crystalcarpetaddition.network.Rule.CCAProtocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
class S2CSendModList {
    @Setter
    private Collection<String> BlackList;
    @Setter
    private boolean Send;
    @Setter
    private boolean Regex;
    private final int version = 2;

    public S2CSendModList(){
        // 无参构造函数, 用于获取version
    }

    public S2CSendModList(Collection<String> blackList, boolean send, boolean regex) {
        BlackList = blackList;
        Send = send;
        Regex = regex;
    }

    @Override
    public String toString() {
        return "SendModList{" +
                "BlackList=" + BlackList +
                ", Send=" + Send +
                ", Regex=" + Regex +
                ", version=" + version +
                '}';
    }
}
