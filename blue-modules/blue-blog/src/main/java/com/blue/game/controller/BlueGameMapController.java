package com.blue.game.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.game.domain.BlueGameMap;
import com.blue.game.service.IBlueGameMapService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 游戏地图Controller
 * 
 * @author ruoyi
 * @date 2024-08-24
 */
@RestController
@RequestMapping("/map")
public class BlueGameMapController extends BaseController
{
    @Autowired
    private IBlueGameMapService blueGameMapService;

    /**
     * 查询游戏地图列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueGameMap blueGameMap)
    {
        startPage();
        List<BlueGameMap> list = blueGameMapService.selectBlueGameMapList(blueGameMap);
        return getDataTable(list);
    }

    /**
     * 导出游戏地图列表
     */
    @RequiresPermissions("game:map:export")
    @Log(title = "游戏地图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameMap blueGameMap)
    {
        List<BlueGameMap> list = blueGameMapService.selectBlueGameMapList(blueGameMap);
        ExcelUtil<BlueGameMap> util = new ExcelUtil<BlueGameMap>(BlueGameMap.class);
        util.exportExcel(response, list, "游戏地图数据");
    }

    /**
     * 获取游戏地图详细信息
     */
    @RequiresPermissions("game:map:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameMapService.selectBlueGameMapById(id));
    }

    /**
     * 新增游戏地图
     */
    @RequiresPermissions("game:map:add")
    @Log(title = "游戏地图", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameMap blueGameMap)
    {
        return toAjax(blueGameMapService.insertBlueGameMap(blueGameMap));
    }

    /**
     * 修改游戏地图
     */
    @RequiresPermissions("game:map:edit")
    @Log(title = "游戏地图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameMap blueGameMap)
    {
        return toAjax(blueGameMapService.updateBlueGameMap(blueGameMap));
    }

    /**
     * 删除游戏地图
     */
    @RequiresPermissions("game:map:remove")
    @Log(title = "游戏地图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameMapService.deleteBlueGameMapByIds(ids));
    }
}
