package com.blue.common.core.enums;

import java.util.HashMap;
import java.util.Map;

/** 游戏标签枚举类 */
public enum GameModeStatus {
    ZOMBIE_ESCAPE(1L, "僵尸逃跑"),
    ZOMBIE_INFECTION(2L, "僵尸感染"),
    HIDE_THE_CAT(3L, "躲猫猫"),
    FULL_TEN(4L, "满十"),
    MG(5L, "MG"),
    MIXED_BATTLE(6L, "混战"),
    TTT(7L, "TTT"),
    ON_HOOK(8L, "挂机大厅"),
    INSCRIPTION_VIEW(9L, "饰品检视"),
    GAME_MODE_STATUS_RELAXATION_COMBAT(10L, "休闲对抗"),
    GAME_MODE_STATUS_ESCAPE_GAME(11L, "越狱搞基"),
    GAME_MODE_STATUS_CLIMBING_SPEED(12L, "攀岩竞速"),
    GAME_MODE_STATUS_CLOTHING_MIXED_BATTLE(13L, "女装混战");



    private final Long code;
    private final String info;

    GameModeStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    //获取全部枚举 返回Map
    public static Map<Long, String> toMap() {
        Map<Long, String> map = new HashMap<Long, String>(GameModeStatus.values().length);
        for (GameModeStatus type : GameModeStatus.values()) {
            map.put(type.getCode(), type.getInfo());
        }
        return map;
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
