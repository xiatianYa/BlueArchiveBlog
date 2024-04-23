package com.blue.blog.entry.dao;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 网站信息对象 blue_website
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
