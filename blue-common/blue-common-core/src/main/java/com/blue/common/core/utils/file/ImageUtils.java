package com.blue.common.core.utils.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 图片处理工具类
 *
 * @author ruoyi
 */
public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath) {
        InputStream is = getFile(imagePath);
        try {
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error("图片加载异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath) {
        try {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        } catch (Exception e) {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     *
     * @param url 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url) {
        InputStream in = null;
        try {
            // 网络地址
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(60 * 1000);
            urlConnection.setDoInput(true);
            in = urlConnection.getInputStream();
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("访问文件异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    // 下载网络图片 获取byte[]
    public static String downloadImageAsResource(String imageUrl, String targetDir, String fileName) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Resource> responseEntity = restTemplate.exchange(
                    imageUrl,
                    HttpMethod.GET,
                    null,
                    Resource.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                try (InputStream inputStream = responseEntity.getBody().getInputStream()) {
                    Path path = Paths.get(targetDir, fileName);
                    Files.createDirectories(path.getParent());
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                    return "https://bluearchive.top/statics/live/" + fileName;
                }
            } else {
                throw new RuntimeException("Failed to download image: " + responseEntity.getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error downloading image", e);
        }
    }
}
