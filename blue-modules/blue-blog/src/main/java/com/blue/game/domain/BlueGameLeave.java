package com.blue.game.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;

/**
 * 游戏留言对象 blue_game_leave
 * 
 * @author ruoyi
 * @date 2024-09-17
 */
public class BlueGameLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 留言类型 */
    @Excel(name = "留言类型")
    private Long leaveType;

    /** 留言消息 */
    @Excel(name = "留言消息")
    private String leaveMessage;

    /** 留言图片 */
    @Excel(name = "留言图片")
    private String leaveImages;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveStart;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveEnd;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLeaveType(Long leaveType) 
    {
        this.leaveType = leaveType;
    }

    public Long getLeaveType() 
    {
        return leaveType;
    }
    public void setLeaveMessage(String leaveMessage) 
    {
        this.leaveMessage = leaveMessage;
    }

    public String getLeaveMessage() 
    {
        return leaveMessage;
    }
    public void setLeaveImages(String leaveImages) 
    {
        this.leaveImages = leaveImages;
    }

    public String getLeaveImages() 
    {
        return leaveImages;
    }
    public void setLeaveStart(Date leaveStart) 
    {
        this.leaveStart = leaveStart;
    }

    public Date getLeaveStart() 
    {
        return leaveStart;
    }
    public void setLeaveEnd(Date leaveEnd) 
    {
        this.leaveEnd = leaveEnd;
    }

    public Date getLeaveEnd() 
    {
        return leaveEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("leaveType", getLeaveType())
            .append("leaveMessage", getLeaveMessage())
            .append("leaveImages", getLeaveImages())
            .append("leaveStart", getLeaveStart())
            .append("leaveEnd", getLeaveEnd())
            .append("createBy", getCreateBy())
            .toString();
    }
}
