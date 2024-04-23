package com.blue.blog.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticleSearchDTO {

    private long total;
    private List<BlueArticleDTO> blueArticleList;
}
