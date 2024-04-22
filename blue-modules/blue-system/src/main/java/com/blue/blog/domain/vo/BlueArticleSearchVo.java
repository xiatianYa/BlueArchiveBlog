package com.blue.blog.domain.vo;

import lombok.Data;

@Data
public class BlueArticleSearchVo {
    private Integer pageNum;
    private Integer pageSize;
    private String searchValue;
}
