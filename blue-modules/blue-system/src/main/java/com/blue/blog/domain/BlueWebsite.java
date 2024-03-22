package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 网站信息对象 blue_website
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
public class BlueWebsite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 网站的名称 */
    @Excel(name = "网站的名称")
    private String websiteName;

    /** 网站的头像 */
    @Excel(name = "网站的头像")
    private String websiteCover;

    /** 网站的地址 */
    @Excel(name = "网站的地址")
    private String websiteUrl;

    /** 网站的描述 */
    @Excel(name = "网站的描述")
    private String websiteDetail;

    /** 网站的备注 */
    @Excel(name = "网站的备注")
    private String websiteRemark;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWebsiteName(String websiteName) 
    {
        this.websiteName = websiteName;
    }

    public String getWebsiteName() 
    {
        return websiteName;
    }
    public void setWebsiteCover(String websiteCover) 
    {
        this.websiteCover = websiteCover;
    }

    public String getWebsiteCover() 
    {
        return websiteCover;
    }
    public void setWebsiteUrl(String websiteUrl) 
    {
        this.websiteUrl = websiteUrl;
    }

    public String getWebsiteUrl() 
    {
        return websiteUrl;
    }
    public void setWebsiteDetail(String websiteDetail) 
    {
        this.websiteDetail = websiteDetail;
    }

    public String getWebsiteDetail() 
    {
        return websiteDetail;
    }
    public void setWebsiteRemark(String websiteRemark) 
    {
        this.websiteRemark = websiteRemark;
    }

    public String getWebsiteRemark() 
    {
        return websiteRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("websiteName", getWebsiteName())
            .append("websiteCover", getWebsiteCover())
            .append("websiteUrl", getWebsiteUrl())
            .append("websiteDetail", getWebsiteDetail())
            .append("websiteRemark", getWebsiteRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
