package com.blue.sort.controller;

import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.sort.domain.BlueProgramToolSort;
import com.blue.sort.service.IBlueProgramToolSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 编程工具分类Controller
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@RestController
@RequestMapping("/toolSort")
public class BlueProgramToolSortController extends BaseController
{
    @Resource
    private IBlueProgramToolSortService blueProgramToolSortService;

    /**
     * 查询编程工具分类列表
     */
    @RequiresPermissions("sort:sort:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueProgramToolSort blueProgramToolSort)
    {
        startPage();
        List<BlueProgramToolSort> list = blueProgramToolSortService.selectBlueProgramToolSortList(blueProgramToolSort);
        return getDataTable(list);
    }

    /**
     * 导出编程工具分类列表
     */
    @RequiresPermissions("sort:sort:export")
    @Log(title = "编程工具分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueProgramToolSort blueProgramToolSort)
    {
        List<BlueProgramToolSort> list = blueProgramToolSortService.selectBlueProgramToolSortList(blueProgramToolSort);
        ExcelUtil<BlueProgramToolSort> util = new ExcelUtil<BlueProgramToolSort>(BlueProgramToolSort.class);
        util.exportExcel(response, list, "编程工具分类数据");
    }

    /**
     * 获取编程工具分类详细信息
     */
    @RequiresPermissions("sort:sort:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueProgramToolSortService.selectBlueProgramToolSortById(id));
    }

    /**
     * 新增编程工具分类
     */
    @RequiresPermissions("sort:sort:add")
    @Log(title = "编程工具分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueProgramToolSort blueProgramToolSort)
    {
        return toAjax(blueProgramToolSortService.insertBlueProgramToolSort(blueProgramToolSort));
    }

    /**
     * 修改编程工具分类
     */
    @RequiresPermissions("sort:sort:edit")
    @Log(title = "编程工具分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueProgramToolSort blueProgramToolSort)
    {
        return toAjax(blueProgramToolSortService.updateBlueProgramToolSort(blueProgramToolSort));
    }

    /**
     * 删除编程工具分类
     */
    @RequiresPermissions("sort:sort:remove")
    @Log(title = "编程工具分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueProgramToolSortService.deleteBlueProgramToolSortByIds(ids));
    }
}
