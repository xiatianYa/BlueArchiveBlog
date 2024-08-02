package com.blue.common.security.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.blue.common.security.utils.SecurityUtils;
import com.blue.system.api.model.LoginUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MyBatisPlus 字段自动注入填充器
 * <a href="https://baomidou.com/pages/4c6bcf/">https://baomidou.com/pages/4c6bcf/</a>
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.handler.MybatisPlusMetaObjectHandler
 * @CreateTime 2023/7/21 - 21:50
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    public static final String CREATE_BY = "createBy";
    public static final String CREATE_TIME = "createTime";
    public static final String DEL_FLAG = "delFlag";

    public static final String UPDATE_BY = "updateBy";
    public static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (metaObject.hasGetter(CREATE_BY)) {
            setFieldValByName(CREATE_BY, loginUser.getUserid().toString(), metaObject);
        }
        if (metaObject.hasGetter(CREATE_TIME)) {
            setFieldValByName(CREATE_TIME, new Date(), metaObject);
        }
        if (metaObject.hasGetter(DEL_FLAG)) {
            setFieldValByName(DEL_FLAG, 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (metaObject.hasGetter(UPDATE_BY)) {
            this.setFieldValByName(UPDATE_BY, loginUser.getUserid().toString(), metaObject);
        }
        if (metaObject.hasGetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, new Date(), metaObject);
        }
    }
}
