package com.blue.auth.service;

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
        // 用户名或密码为空
        if (StringUtils.isAnyBlank(registerBody.getUsername(), registerBody.getPassword())) {
            throw new ServiceException("用户/密码必须填写");
        }
        // 未选择头像
        if (!StringUtils.isNotEmpty(registerBody.getAvater())) {
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
        // 密码长度不正确
        if (registerBody.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || registerBody.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }
        // 密码长度不一致
        if (!registerBody.getPassword().equals(registerBody.getEntryPassword())) {
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
        // 注册用户信息
        SysUser sysUser = new SysUser();
        // 设置用户名称
        sysUser.setUserName(registerBody.getUsername());
        //设置用户NickName
        sysUser.setNickName(registerBody.getUsername());
        // 设置用户密码
        sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
        // 设置用户头像
        sysUser.setAvatar(registerBody.getAvater());
        //设置用户角色为普通用户
        sysUser.setRoleId(100L);
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);
        if (R.FAIL == registerResult.getCode()) {
            throw new ServiceException(registerResult.getMsg());
        }
        //清除Redis验证码缓存
        redisService.deleteObject(registerBody.getPhone());
        //返回成功信息
        recordLogService.recordLogininfor(registerBody.getUsername(), Constants.REGISTER, "注册成功");
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
}
