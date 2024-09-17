package com.blue.game.domain.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiliUserDataVo {
    @JSONField(name = "room_id")
    private Long roomId; // 直播间长ID
    @JSONField(name = "uid")
    private Long uid; // 主播用户mid
    @JSONField(name = "live_status")
    private Integer liveStatus; // 直播状态
    @JSONField(name = "live_url")
    private String liveUrl; // 直播间网页url
    @JSONField(name = "title")
    private String title; // 直播间标题
    @JSONField(name = "parent_area_name")
    private String parentAreaName; // 直播间父分区名称
    @JSONField(name = "area_name")
    private String areaName; // 直播间分区名称
    @JSONField(name = "live_time")
    private String liveTime; // 开播时间 yyyy-MM-dd HH:mm:ss
    @JSONField(name = "online")
    private Long online; // 在线人数
    @JSONField(name = "uname")
    private String uname; // 主播用户名
    private String avatarUrl; // 直播间头像url
    private String bgUrl; //直播间背景url
}
