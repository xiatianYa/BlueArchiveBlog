package com.blue.blog.entry.vo;

import lombok.Data;

@Data
public class BlueArticleSearchVo {
    private Integer pageNum;
    private Integer pageSize;
    private String searchValue;
}
