package com.blue.echarts.controller;

import com.blue.common.core.web.domain.AjaxResult;
import com.blue.echarts.domain.UserHeatMapDataVo;
import com.blue.echarts.service.BlueEchartsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据统计Controller
 *
 * @author summer
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/echarts")
public class BlueEchartsController {
    @Resource
    private BlueEchartsService blueEchartsService;
    /**
     * 查询用户热力图数据
     */
    @GetMapping("/selectUserHeatMapData")
    public AjaxResult selectUserHeatMapData() {
        List<UserHeatMapDataVo> data=blueEchartsService.selectUserHeatMapData();
        return AjaxResult.success(data);
    }
}
