package com.blue.auth.controller;

import com.blue.auth.form.LoginBody;
import com.blue.auth.form.OauthBody;
import com.blue.auth.form.RegisterBody;
import com.blue.auth.service.SysLoginService;
import com.blue.common.core.domain.R;
import com.blue.common.core.utils.JwtUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.security.auth.AuthUtil;
import com.blue.common.security.service.TokenService;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;

/**
 * token 控制
 *
 * @author ruoyi
 */
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;
    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }
    @PostMapping("oauth2/login")
    public R<?> oauthLogin(@RequestBody OauthBody form) {
        // 用户登录
        LoginUser userInfo =sysLoginService.oauthLogin(form);
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request) {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("userRegister")
    public R<?> register(@RequestBody RegisterBody registerBody) {
        // 用户注册
        String msg = sysLoginService.register(registerBody);
        return R.ok(msg);
    }
    @PostMapping("code")
    public R<?> getPhoneCode(@RequestBody String phone){
        String phoneCode=sysLoginService.getPhoneCode(phone);
        return R.ok(phoneCode);
    }
}
