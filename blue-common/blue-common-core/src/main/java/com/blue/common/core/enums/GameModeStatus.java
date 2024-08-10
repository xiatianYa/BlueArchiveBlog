package com.blue.common.core.enums;

/** 游戏标签枚举类 */
public enum GameModeStatus {
    ZOMBIE_ESCAPE(1L, "僵尸逃跑"),
    ZOMBIE_INFECTION(2L, "僵尸感染"),
    HIDE_THE_CAT(3L, "躲猫猫"),
    FULL_TEN(4L, "满十"),
    MG(5L, "MG"),
    MIXED_BATTLE(6L, "混战"),
    TTT(7L, "TTT");


    private final Long code;
    private final String info;

    GameModeStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    //通过Code 返回 Info
    public static String getInfoByCode(Long code) {
        for (GameModeStatus gameModeStatus : GameModeStatus.values()) {
            if (gameModeStatus.getCode().equals(code)) {
                return gameModeStatus.getInfo();
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
