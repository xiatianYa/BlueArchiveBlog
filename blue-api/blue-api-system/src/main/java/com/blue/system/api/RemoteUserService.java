package com.blue.system.api;

import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.constant.ServiceNameConstants;
import com.blue.common.core.domain.R;
import com.blue.system.api.domain.SysUser;
import com.blue.system.api.factory.RemoteUserFallbackFactory;
import com.blue.system.api.model.LoginUser;
import com.blue.system.api.model.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source   请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 通过用户名Id查询用户信息
     *
     * @param userId 用户ID
     * @param source   请求来源
     * @return 结果
     */
    @GetMapping( "/user/infoById/{userId}")
    public R<UserVo> getUserInfoById(@PathVariable("userId") Long userId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
    /**
     * 通过用户名Id查询用户信息
     *
     * @param openId OpenId
     * @param source   请求来源
     * @return 结果
     */
    @GetMapping( "/user/infoByOpenId/{openId}")
    public R<LoginUser> getUserInfoByOpenId(@PathVariable("openId") String openId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source  请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
