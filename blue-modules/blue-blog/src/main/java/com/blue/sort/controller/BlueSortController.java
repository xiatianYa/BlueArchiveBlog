package com.blue.sort.controller;

import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.sort.domain.BlueSort;
import com.blue.sort.service.IBlueSortService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 分类Controller
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/sort")
public class BlueSortController extends BaseController
{
    @Resource
    private IBlueSortService blueSortService;

    /**
     * 查询分类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueSort blueSort)
    {
        startPage();
        List<BlueSort> list = blueSortService.selectBlueSortList(blueSort);
        return getDataTable(list);
    }

    /**
     * 导出分类列表
     */
    @RequiresPermissions("blog:sort:export")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueSort blueSort)
    {
        List<BlueSort> list = blueSortService.selectBlueSortList(blueSort);
        ExcelUtil<BlueSort> util = new ExcelUtil<BlueSort>(BlueSort.class);
        util.exportExcel(response, list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @RequiresPermissions("blog:sort:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueSortService.selectBlueSortById(id));
    }

    /**
     * 新增分类
     */
    @RequiresPermissions("blog:sort:add")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueSort blueSort)
    {
        return toAjax(blueSortService.insertBlueSort(blueSort));
    }

    /**
     * 修改分类
     */
    @RequiresPermissions("blog:sort:edit")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueSort blueSort)
    {
        return toAjax(blueSortService.updateBlueSort(blueSort));
    }

    /**
     * 删除分类
     */
    @RequiresPermissions("blog:sort:remove")
    @Log(title = "分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueSortService.deleteBlueSortByIds(ids));
    }
}
