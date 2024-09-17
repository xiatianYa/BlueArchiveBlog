package com.blue.system.api;

import com.blue.common.core.constant.ServiceNameConstants;
import com.blue.system.api.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "remoteArticleService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteArticleService {

}
