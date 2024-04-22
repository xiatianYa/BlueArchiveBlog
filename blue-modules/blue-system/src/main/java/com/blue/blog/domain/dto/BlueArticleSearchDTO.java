package com.blue.blog.domain.dto;

import com.blue.elastic.dao.BlueArticleDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueArticleSearchDTO {

    private long total;
    private List<BlueArticleDAO> blueArticleList;
}
