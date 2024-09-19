package com.blue.common.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum GameLeaveTypeStatus {
   //普通留言
    T1(1L, "闲聊"),
    //活动留言
    T2(2L, "问题"),
    //活动留言
    T3(3L, "活动");


    private final Long code;
    private final String info;

    GameLeaveTypeStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    //获取全部枚举 返回Map
    public static Map<Long, String> toMap() {
        Map<Long, String> map = new HashMap<Long, String>(GameLeaveTypeStatus.values().length);
        for (GameLeaveTypeStatus type : GameLeaveTypeStatus.values()) {
            map.put(type.getCode(), type.getInfo());
        }
        return map;
    }

    //通过Code 返回 Info
    public static String getInfoByCode(Long code) {
        for (GameLeaveTypeStatus gameModeStatus : GameLeaveTypeStatus.values()) {
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
