package com.blue.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 相册对象 blue_photo
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BluePhoto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 分类id */
    @Excel(name = "分类id")
    private Long sortId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    @TableField(exist = false)
    private String userName;
    /** 图片名称 */
    @Excel(name = "图片名称")
    private String photoName;
    /** 相册图片地址 */
    @Excel(name = "相册图片地址")
    private String photoUrl;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 逻辑删除 */
    private Integer delFlag;

}
