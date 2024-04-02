package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 二创信息对象 blue_erchuang
 * 
 * @author ruoyi
 * @date 2024-04-02
 */
public class BlueErchuang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 二创作者 */
    @Excel(name = "二创作者")
    private String ecAuthor;

    /** 二创标题 */
    @Excel(name = "二创标题")
    private String ecName;

    /** 二创图片 */
    @Excel(name = "二创图片")
    private String ecAvater;

    /** 二创路径 */
    @Excel(name = "二创路径")
    private String ecUrl;

    /** 二创发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "二创发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ecPublish;

    /** 二创简介 */
    @Excel(name = "二创简介")
    private String ecSynopsis;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEcAuthor(String ecAuthor) 
    {
        this.ecAuthor = ecAuthor;
    }

    public String getEcAuthor() 
    {
        return ecAuthor;
    }
    public void setEcName(String ecName) 
    {
        this.ecName = ecName;
    }

    public String getEcName() 
    {
        return ecName;
    }
    public void setEcAvater(String ecAvater) 
    {
        this.ecAvater = ecAvater;
    }

    public String getEcAvater() 
    {
        return ecAvater;
    }
    public void setEcUrl(String ecUrl) 
    {
        this.ecUrl = ecUrl;
    }

    public String getEcUrl() 
    {
        return ecUrl;
    }
    public void setEcPublish(Date ecPublish) 
    {
        this.ecPublish = ecPublish;
    }

    public Date getEcPublish() 
    {
        return ecPublish;
    }
    public void setEcSynopsis(String ecSynopsis) 
    {
        this.ecSynopsis = ecSynopsis;
    }

    public String getEcSynopsis() 
    {
        return ecSynopsis;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ecAuthor", getEcAuthor())
            .append("ecName", getEcName())
            .append("ecAvater", getEcAvater())
            .append("ecUrl", getEcUrl())
            .append("ecPublish", getEcPublish())
            .append("ecSynopsis", getEcSynopsis())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
