package crystal0404.crystalcarpetaddition.Rule.CCAProtocol;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
public class Json {
    @Setter
    private Collection<String> BlackList;
    private final int Version = 1;

    public Json(Collection<String> blackList) {
        BlackList = blackList;
    }

    @Override
    public String toString() {
        return "Json{" +
                "BlackList=" + BlackList +
                ", Version=" + Version +
                '}';
    }
}
