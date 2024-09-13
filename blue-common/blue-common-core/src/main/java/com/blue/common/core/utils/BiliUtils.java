package com.blue.common.core.utils;

import com.alibaba.fastjson2.JSONObject;
import com.blue.common.core.utils.file.ImageUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class BiliUtils {
    public static String getBiliLiveApi(List<String> uids){
        try {
            RestTemplate restTemplate = new RestTemplate();
            StringBuilder params= new StringBuilder();
            for (String uid : uids) {
                params.append("room_ids=").append(uid).append("&");
            }
            URI uri = new URI("https://api.live.bilibili.com/xlive/web-room/v1/index/getRoomBaseInfo?req_biz=web_room_componet&" + params);
            return restTemplate.getForObject(uri, String.class);
        }catch (Exception e){
            System.out.println("直播间信息获取失败!");
            return null;
        }
    }

    public static byte[] getBiliLiveUserInfoApi(Long roomId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = new URI("https://api.live.bilibili.com/live_user/v1/UserInfo/get_anchor_in_room?roomid="+roomId);
            String jsonString = restTemplate.getForObject(uri, String.class);
            String userCoverUrl = JSONObject.parseObject(jsonString).getJSONObject("data").getJSONObject("info").getString("face");
            return ImageUtils.readFile(userCoverUrl);
        }catch (Exception e){
            System.out.println("直播间信息获取失败!");
            return null;
        }
    }
}
