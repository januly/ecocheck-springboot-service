package lk.ijse.cmjd114_115.ecocheck;

import java.util.UUID;

public class IDGenerate {
    public static String userId() {
        return "USR" + shortId();
    }

    public static String actionCategoryId() {
        return "CAT" + shortId();
    }

    public static String climateActionId() {
        return "ACT" + shortId();
    }

    public static String userActionId() {
        return "UA" + shortId();
    }

    public static String goalId() {
        return "GOAL" + shortId();
    }

    private static String shortId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}

