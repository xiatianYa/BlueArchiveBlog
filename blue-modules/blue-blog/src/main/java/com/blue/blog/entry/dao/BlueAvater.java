package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户头像列存储对象 blue_avater
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueAvater extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 头像id */
    private Long id;

    /** 头像路径 */
    @Excel(name = "头像路径")
    private String avaterUrl;

}
