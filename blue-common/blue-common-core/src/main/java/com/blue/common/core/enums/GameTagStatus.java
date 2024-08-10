package com.blue.common.core.enums;

/** 游戏标签枚举类 */
public enum GameTagStatus {
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
    BOMB(12L, "爆破");


    private final Long code;
    private final String info;

    GameTagStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    //通过Code 返回 Info
    public static String getInfoByCode(Long code) {
        for (GameTagStatus gameTagStatus : GameTagStatus.values()) {
            if (gameTagStatus.getCode().equals(code)) {
                return gameTagStatus.getInfo();
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
