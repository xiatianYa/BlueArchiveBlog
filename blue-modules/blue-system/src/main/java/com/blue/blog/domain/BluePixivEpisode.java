package com.blue.blog.domain;

import com.blue.common.core.annotation.Excel;
import com.blue.common.core.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 番剧集存储对象 blue_pixiv_episode
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BluePixivEpisode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 番剧ID */
    @Excel(name = "番剧ID")
    private Long pixivId;

    /** 番剧集 */
    @Excel(name = "番剧集")
    private Long pixivChapters;

    /** 番剧地址 */
    @Excel(name = "番剧地址")
    private String pixivUrl;

}
