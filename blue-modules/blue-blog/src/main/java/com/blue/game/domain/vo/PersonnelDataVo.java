package com.blue.game.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonnelDataVo {
    /** 社区名称 */
    private List<String> communityNameList;
    /** 时间 */
    private List<String> xAxisData;
    /** 数据列表 */
    private List<List<Integer>> yAxisData;
}
