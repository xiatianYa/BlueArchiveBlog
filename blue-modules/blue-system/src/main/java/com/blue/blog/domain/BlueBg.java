package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 网站背景资源信息对象 blue_bg
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueBg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 背景类型 */
    @Excel(name = "背景类型")
    private String bgType;

    /** 背景路径 */
    @Excel(name = "背景路径")
    private String bgUrl;

    /** 背景位置类型 */
    @Excel(name = "背景位置类型")
    private String bgPosition;
}
