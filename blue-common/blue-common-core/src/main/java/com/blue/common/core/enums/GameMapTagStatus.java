package com.blue.common.core.enums;

import java.util.HashMap;
import java.util.Map;

/** 游戏标签枚举类 */
public enum GameMapTagStatus {
    ENTERTAINMENT(1L, "娱乐"),
    JUMP(2L, "跳刀"),
    CROSSING(3L, "闯关"),
    LINE(4L, "长征"),
    BULLET(5L, "弹幕"),
    SLIDE(6L, "滑翔"),
    NEW(7L, "新手"),
    EXPERT(8L, "高手"),
    CLASSIC(9L, "经典"),
    RARE(10L, "冷门"),
    PERSON(11L, "人质"),
    BOMB(12L, "爆破"),
    OBJ(13L,"OBJ"),
    FF(14L,"狒狒"),
    LU_FEN(15L,"卤粉");

    private final Long code;
    private final String info;

    GameMapTagStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    //获取全部枚举 返回Map
    public static java.util.Map<Long, String> toMap() {
        Map<Long, String> map = new HashMap<Long, String>(GameMapTagStatus.values().length);
        for (GameMapTagStatus type : GameMapTagStatus.values()) {
            map.put(type.getCode(), type.getInfo());
        }
        return map;
    }

    //通过Code 返回 Info
    public static String getInfoByCode(Long code) {
        for (GameMapTagStatus gameMapTagStatus : GameMapTagStatus.values()) {
            if (gameMapTagStatus.getCode().equals(code)) {
                return gameMapTagStatus.getInfo();
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