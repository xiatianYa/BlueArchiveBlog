package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 友链申请信息对象 blue_friend_info
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueFriendInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 申请友链接口 */
    @Excel(name = "申请友链接口")
    private String friendUrl;

    /** 申请友链内容 */
    @Excel(name = "申请友链内容")
    private String friendContent;

    /** 申请友链备注 */
    @Excel(name = "申请友链备注")
    private String friendDetail;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFriendUrl(String friendUrl) 
    {
        this.friendUrl = friendUrl;
    }

    public String getFriendUrl() 
    {
        return friendUrl;
    }
    public void setFriendContent(String friendContent) 
    {
        this.friendContent = friendContent;
    }

    public String getFriendContent() 
    {
        return friendContent;
    }
    public void setFriendDetail(String friendDetail) 
    {
        this.friendDetail = friendDetail;
    }

    public String getFriendDetail() 
    {
        return friendDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("friendUrl", getFriendUrl())
            .append("friendContent", getFriendContent())
            .append("friendDetail", getFriendDetail())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
