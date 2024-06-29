package com.blue.common.core.enums;

/** 审核枚举类 */
public enum AuditingStatus {
    //审核中
    OK(0L, "未审核"),WAIT(1L, "审核中"),DISABLE(2L, "已通过"), DELETED(3L, "未通过");

    private final Long code;
    private final String info;

    AuditingStatus(Long code, String info) {
        this.code = code;
        this.info = info;
    }

    public Long getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
