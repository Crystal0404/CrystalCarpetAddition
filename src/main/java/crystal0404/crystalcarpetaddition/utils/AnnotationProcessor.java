package crystal0404.crystalcarpetaddition.utils;

import crystal0404.crystalcarpetaddition.api.annotation.Condition;
import crystal0404.crystalcarpetaddition.api.annotation.Restriction;

public abstract class AnnotationProcessor {
    public static boolean shouldRegister(Restriction restriction) {
        for (Condition condition : restriction.conflict()) {
            if (CCAUtils.isLoad(condition.value(), condition.versionPredicates())) {
                return false;
            }
        }

        for (Condition condition : restriction.require()) {
            if (!CCAUtils.isLoad(condition.value(), condition.versionPredicates())) {
                return false;
            }
        }

        return true;
    }
}
