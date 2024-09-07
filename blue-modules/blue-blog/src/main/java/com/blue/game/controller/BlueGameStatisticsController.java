package com.blue.game.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.blue.game.domain.vo.PersonnelDataVo;
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
import com.blue.game.domain.BlueGameStatistics;
import com.blue.game.service.IBlueGameStatisticsService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 数据统计Controller
 * 
 * @author ruoyi
 * @date 2024-09-01
 */
@RestController
@RequestMapping("/statistics")
public class BlueGameStatisticsController extends BaseController
{
    @Autowired
    private IBlueGameStatisticsService blueGameStatisticsService;

    /**
     * 查询数据统计列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueGameStatistics blueGameStatistics)
    {
        startPage();
        List<BlueGameStatistics> list = blueGameStatisticsService.selectBlueGameStatisticsList(blueGameStatistics);
        return getDataTable(list);
    }

    /**
     * 查询全部数据统计列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        return success(blueGameStatisticsService.selectBlueGameStatisticsListAll());
    }

    /**
     * 导出数据统计列表
     */
    @RequiresPermissions("game:statistics:export")
    @Log(title = "数据统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameStatistics blueGameStatistics)
    {
        List<BlueGameStatistics> list = blueGameStatisticsService.selectBlueGameStatisticsList(blueGameStatistics);
        ExcelUtil<BlueGameStatistics> util = new ExcelUtil<BlueGameStatistics>(BlueGameStatistics.class);
        util.exportExcel(response, list, "数据统计数据");
    }

    /**
     * 获取数据统计详细信息
     */
    @RequiresPermissions("game:statistics:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameStatisticsService.selectBlueGameStatisticsById(id));
    }

    /**
     * 新增数据统计
     */
    @RequiresPermissions("game:statistics:add")
    @Log(title = "数据统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameStatistics blueGameStatistics)
    {
        return toAjax(blueGameStatisticsService.insertBlueGameStatistics(blueGameStatistics));
    }

    /**
     * 修改数据统计
     */
    @RequiresPermissions("game:statistics:edit")
    @Log(title = "数据统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameStatistics blueGameStatistics)
    {
        return toAjax(blueGameStatisticsService.updateBlueGameStatistics(blueGameStatistics));
    }

    /**
     * 删除数据统计
     */
    @RequiresPermissions("game:statistics:remove")
    @Log(title = "数据统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameStatisticsService.deleteBlueGameStatisticsByIds(ids));
    }
}
