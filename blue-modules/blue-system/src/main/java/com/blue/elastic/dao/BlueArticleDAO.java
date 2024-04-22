package com.blue.elastic.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章对象 blue_article
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticleDAO implements Serializable
{

    /** id */
    private Long id;

    /** 文章名称 */
    private String articleName;
    /** 文章描述 */
    private String articleDescribe;
}
