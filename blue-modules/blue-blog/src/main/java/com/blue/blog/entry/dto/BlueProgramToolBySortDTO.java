package com.blue.blog.entry.dto;

import com.blue.blog.entry.dao.BlueProgramTool;
import com.blue.common.core.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlueProgramToolBySortDTO {
    /** 工具分类名称 */
    @Excel(name = "音乐分类")
    private String sortName;

    /** 工具列表 */
    @Excel(name = "音乐分类")
    private List<BlueProgramTool> blueProgramToolList;
}
