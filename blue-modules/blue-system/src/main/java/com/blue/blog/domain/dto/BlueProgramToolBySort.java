package com.blue.blog.domain.dto;

import com.blue.blog.domain.BlueProgramTool;
import com.blue.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueProgramToolBySort {
    /** 工具分类名称 */
    @Excel(name = "音乐分类")
    private String sortName;

    /** 工具列表 */
    @Excel(name = "音乐分类")
    private List<BlueProgramTool> blueProgramToolList;
}
