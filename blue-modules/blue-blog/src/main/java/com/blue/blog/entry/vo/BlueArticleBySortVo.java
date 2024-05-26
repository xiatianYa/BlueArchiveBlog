package com.blue.blog.entry.vo;

import com.blue.blog.entry.dao.BlueArticle;
import lombok.Data;

import java.util.List;

@Data
public class BlueArticleBySortVo {
    private Long sortId;
    private List<BlueArticle> blueArticleList;
}
