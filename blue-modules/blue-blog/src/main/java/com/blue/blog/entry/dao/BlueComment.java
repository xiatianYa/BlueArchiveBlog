package com.blue.blog.entry.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论对象 blue_comment
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 是否父节点 */
    @Excel(name = "是否父节点")
    private Long parentId;

    /** 回复类型 */
    @Excel(name = "回复类型")
    private Long commentType;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String commentContent;

    /** 通用ID */
    @Excel(name = "通用ID")
    private String commonId;
    /** @用户姓名 */
    @TableField(exist = false)
    private String atUserName;

    /** 回复人姓名 */
    @TableField(exist = false)
    private String userName;

    /** 回复人头像 */
    @TableField(exist = false)
    private String userAvatar;


    /** 子回复 */
    @TableField(exist = false)
    private List<BlueComment> replyList;
}
