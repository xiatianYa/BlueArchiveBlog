package com.blue.echarts.service;

import com.blue.echarts.domain.UserHeatMapDataVo;

import java.util.List;

public interface BlueEchartsService {
    List<UserHeatMapDataVo> selectUserHeatMapData();
}
