package com.blue.system.api.factory;

import com.blue.common.core.domain.R;
import com.blue.system.api.RemoteArticleService;
import com.blue.system.api.RemoteFileService;
import com.blue.system.api.domain.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteArticleFallbackFactory implements FallbackFactory<RemoteArticleService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);

    @Override
    public RemoteArticleService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteArticleService() {

        };
    }
}
