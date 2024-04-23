package com.blue.blog.entry.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章对象 blue_article
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticleDTO implements Serializable
{

    /** id */
    private Long id;

    /** 文章名称 */
    private String articleName;

    /** 文章描述 */
    private String articleDescribe;

    /** 用户名称 */
    private String userName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
