package com.blue.common.core.utils;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class SteamUtils {
    public static String getSteamApiServer(List<String> pathList){
        try {
            RestTemplate restTemplate = new RestTemplate();
            StringBuilder paths= new StringBuilder();
            for (String path : pathList) {
                paths.append("paths=").append(path).append("&");
            }
            return restTemplate.getForObject(new URI("http://inadvertently.top/steamApi/?"+ paths), String.class);
        }catch (Exception e){
            System.out.println("服务器信息获取失败!");
            return null;
        }
    }
}
