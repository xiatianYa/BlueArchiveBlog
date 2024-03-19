package com.blue.common.core.enums;

/** 审核枚举类 */
public enum AuditingStatus {
    OK(0L, "未审核"), DISABLE(1L, "已通过"), DELETED(2L, "未通过");

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
