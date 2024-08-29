package com.blue.auth.service;

import com.alibaba.fastjson2.JSONObject;
import com.blue.auth.form.OauthBody;
import com.blue.auth.form.QQInfoBody;
import com.blue.auth.form.RegisterBody;
import com.blue.common.core.constant.CacheConstants;
import com.blue.common.core.constant.Constants;
import com.blue.common.core.constant.SecurityConstants;
import com.blue.common.core.constant.UserConstants;
import com.blue.common.core.domain.R;
import com.blue.common.core.enums.ExchangeStatus;
import com.blue.common.core.enums.UserStatus;
import com.blue.common.core.exception.ServiceException;
import com.blue.common.core.text.Convert;
import com.blue.common.core.utils.RandomUtils;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.core.utils.ip.IpUtils;
import com.blue.common.redis.service.RedisService;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.RemoteUserService;
import com.blue.system.api.domain.SysUser;
import com.blue.system.api.model.LoginUser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysRecordLogService recordLogService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 登录
     */
    public LoginUser login(String username, String password) {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password)) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        // IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "很遗憾，访问IP已被列入系统黑名单");
            throw new ServiceException("很遗憾，访问IP已被列入系统黑名单");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }

        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        passwordService.validate(user, password);
        recordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }

    public void logout(String loginName) {
        recordLogService.recordLogininfor(loginName, Constants.LOGOUT, "退出成功");
    }

    /**
     * 注册
     */
    public String register(RegisterBody registerBody) {
        // 用户名 用户昵称 密码 为空就进入
        if (!StringUtils.isNotEmpty(registerBody.getUserName())
                || !StringUtils.isNotEmpty(registerBody.getNickName())
                || !StringUtils.isNotEmpty(registerBody.getPassWord())) {
            throw new ServiceException("用户名|用户昵称|密码 为空");
       }
        // 未选择头像
        if (!StringUtils.isNotEmpty(registerBody.getAvatar())) {
            throw new ServiceException("请选择头像");
        }
        //校验手机号是否为空
        if (!StringUtils.isNotEmpty(registerBody.getPhone())){
            throw new ServiceException("请先填写手机号");
        }
        // 用户验证码错误 或 未获取验证码
        if (StringUtils.isNotEmpty(registerBody.getSms())){
            //通过手机号去校验验证码
            String cacheCode= redisService.getCacheObject(registerBody.getPhone());
            if (!StringUtils.isNotNull(cacheCode)){
                throw new ServiceException("请先获取验证码");
            }
            String phoneCode = registerBody.getSms();
            if (!cacheCode.equals(phoneCode)){
                throw new ServiceException("验证码错误,请重新输入");
            }
        }else {
            throw new ServiceException("请先填写验证码");
        }
        //用户名 用户名称长度校验
        if (registerBody.getUserName().length() < UserConstants.USERNAME_MIN_LENGTH
                || registerBody.getUserName().length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("用户名长度必须在2到20个字符之间");
        }
        if (registerBody.getNickName().length() < UserConstants.USERNAME_MIN_LENGTH
                || registerBody.getNickName().length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new ServiceException("用户名长度必须在2到20个字符之间");
        }
        // 密码长度不正确
        if (registerBody.getPassWord().length() < UserConstants.PASSWORD_MIN_LENGTH
                || registerBody.getPassWord().length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }
        // 密码不一致
        if (!registerBody.getPassWord().equals(registerBody.getEntryPassword())) {
            throw new ServiceException("两次密码不一致");
        }
        //校验手机号格式是否正确
        if (registerBody.getPhone().length()!=11){
            throw new ServiceException("请填写11位的手机号");
        }
        //校验手机号格式是否是纯数字
        if (!registerBody.getPhone().matches("^\\d+$")){
            throw new ServiceException("请填写正确的手机号");
        }
        //校验用户名是否已存在
        R<?> userResult = remoteUserService.getUserInfo(registerBody.getUserName(), SecurityConstants.INNER);
        if (R.FAIL != userResult.getCode()){
            recordLogService.recordLogininfor(registerBody.getUserName(), Constants.LOGIN_FAIL, "登录用户名已存在");
            throw new ServiceException("登录用户：" + registerBody.getUserName() + " 已存在");
        }

        // 注册用户信息
        SysUser sysUser = new SysUser();
        // 设置用户名称
        sysUser.setUserName(registerBody.getUserName());
        //设置用户NickName
        sysUser.setNickName(registerBody.getNickName());
        //设置用户手机号
        sysUser.setPhonenumber(registerBody.getPhone());
        // 设置用户密码
        sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassWord()));
        // 设置用户头像
        sysUser.setAvatar(registerBody.getAvatar());
        //设置用户角色为普通用户
        sysUser.setRoleId(100L);
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        //清除Redis验证码缓存
        redisService.deleteObject(registerBody.getPhone());
        //返回成功信息
        recordLogService.recordLogininfor(registerBody.getUserName(), Constants.REGISTER, "注册成功");
        return "注册成功";
    }
    /**
     * 获取验证码
     */
    public String getPhoneCode(String phone) {
        //获取本次获取验证码IP
        String ipAddr = IpUtils.getIpAddr();
        //校验手机号是否为空
        if (!StringUtils.isNotEmpty(phone)){
            throw new ServiceException("请先填写手机号");
        }
        //校验手机号格式是否正确
        if (phone.length()!=11){
            throw new ServiceException("请填写11位的手机号");
        }
        //校验手机号格式是否是纯数字
        if (!phone.matches("^\\d+$")){
            throw new ServiceException("请填写正确的手机号");
        }
        //查看是不是同一个IP发送验证码
        String ipAddrCode = redisService.getCacheObject(ipAddr);
        if (StringUtils.isNotNull(ipAddrCode)){
            throw new ServiceException("同一IP发送验证码间隔时间12小时,请稍后再试");
        }
        //查看是否存在验证码
        String cacheCode = redisService.getCacheObject(phone);
        if (StringUtils.isNotNull(cacheCode)){
            throw new ServiceException("验证码已发送(保存时间12小时),请稍后再试");
        }
        String phoneCode = RandomUtils.getRandom(6);
        Map<String,String> map=new HashMap<>(2);
        map.put("phone",phone);
        map.put("phoneCode",phoneCode);
        rabbitTemplate.convertAndSend(ExchangeStatus.SMS_EXCHANGE_SEND.getExchangeName(),ExchangeStatus.SMS_EXCHANGE_SEND.getRoutingKey(),map);
        redisService.setCacheObject(phone, phoneCode,(long)720*60, TimeUnit.SECONDS);
        redisService.setCacheObject(ipAddr,ipAddr,(long)720*60, TimeUnit.SECONDS);
        return "短信验证码发送成功";
    }

    public LoginUser oauthLogin(OauthBody form) {
        //第三方用户对象
        QQInfoBody qqInfoBody=null;
        //当用户form为空
        if (StringUtils.isNull(form.getAccessToken()) || StringUtils.isNull(form.getType()) || StringUtils.isNull(form.getOpenId())){
            throw new ServiceException("非法参数!");
        }
        try {
            URL url = new URL("https://graph.qq.com/user/get_user_info?oauth_consumer_key=102129326&access_token="+form.getAccessToken()+"&openid="+form.getOpenId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // 读取响应
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    qqInfoBody= JSONObject.parseObject(line, QQInfoBody.class);
                }
            }
            // 关闭连接
            conn.disconnect();
            if (StringUtils.isNull(qqInfoBody)){
                throw new Exception();
            }
        } catch (Exception e) {
            throw new ServiceException("获取第三方用户信息失败");
        }
        //通过用户OpenId 判断用户是否注册过
        R<LoginUser>  userResult = remoteUserService.getUserInfoByOpenId(form.getOpenId(), SecurityConstants.INNER);
        //用户不存在 则进行注册
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData())) {
            //校验用户名是否已存在
            R<LoginUser> isLong = remoteUserService.getUserInfo(qqInfoBody.getNickname(), SecurityConstants.INNER);
            if (StringUtils.isNotNull(isLong) && StringUtils.isNotNull(isLong.getData())) {
                recordLogService.recordLogininfor(qqInfoBody.getNickname(), Constants.LOGIN_FAIL, "登录用户名已存在");
                throw new ServiceException("登录用户：" + qqInfoBody.getNickname() + " 已存在");
            }
            recordLogService.recordLogininfor(form.getOpenId(),Constants.LOGIN_FAIL, "第三方登录用户未注册");
            // 注册用户信息
            SysUser sysUser = new SysUser();
            //设置OpenId
            sysUser.setOpenId(form.getOpenId());
            // 设置用户名称
            sysUser.setUserName(qqInfoBody.getNickname());
            //设置用户NickName
            sysUser.setNickName(qqInfoBody.getNickname());
            // 设置用户密码
            sysUser.setPassword(SecurityUtils.encryptPassword("bluearchive"));
            // 设置用户头像
            if (StringUtils.isNotNull(qqInfoBody.getFigureurl_qq_2())){
                sysUser.setAvatar(qqInfoBody.getFigureurl_qq_2());
            }
            sysUser.setAvatar(qqInfoBody.getFigureurl_qq_1());
            //设置用户角色为普通用户
            sysUser.setRoleId(100L);
            R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);
            if (R.FAIL == registerResult.getCode()) {
                throw new ServiceException(registerResult.getMsg());
            }
            //返回成功信息
            recordLogService.recordLogininfor(sysUser.getUserName(), Constants.REGISTER, "注册成功");
            //重新获取用户信息
            userResult = remoteUserService.getUserInfoByOpenId(form.getOpenId(), SecurityConstants.INNER);
            return userResult.getData();
        }
        //用户存在则返回token
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            recordLogService.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + user.getUserName() + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            recordLogService.recordLogininfor(user.getUserName(), Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + user.getUserName() + " 已停用");
        }
        recordLogService.recordLogininfor(user.getNickName(), Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }
}
