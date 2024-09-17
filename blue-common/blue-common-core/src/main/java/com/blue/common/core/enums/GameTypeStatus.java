package com.blue.common.core.enums;


import java.util.Map;

/** 游戏标签枚举类 */
public enum GameTypeStatus {
    T1(1L, "简单"),
    T2(2L, "普通"),
    T3(3L, "困难"),
    T4(4L, "高难"),
    T5(5L, "极难"),
    T6(6L, "至难"),
    T7(7L, "绝难");


    private final Long code;
    private final String info;

    GameTypeStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    public static Map<Long, String> toMap() {
        java.util.Map<Long, String> map = new java.util.HashMap<Long, String>(GameTypeStatus.values().length);
        for (GameTypeStatus type : GameTypeStatus.values()) {
            map.put(type.getCode(), type.getInfo());
        }
        return map;
    }

    //通过Code 返回 Info
    public static String getInfoByCode(Long code) {
        for (GameTypeStatus gameTypeStatus : GameTypeStatus.values()) {
            if (gameTypeStatus.getCode().equals(code)) {
                return gameTypeStatus.getInfo();
            }
        }
        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
