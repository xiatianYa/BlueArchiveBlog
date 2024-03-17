package com.blue.sort.controller;

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
import com.blue.sort.domain.BlueSort;
import com.blue.sort.service.IBlueSortService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 分类Controller
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/sort")
public class BlueSortController extends BaseController {
    @Autowired
    private IBlueSortService blueSortService;

    /**
     * 查询分类列表
     */
    @RequiresPermissions("sort:sort:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueSort blueSort) {
        startPage();
        List<BlueSort> list = blueSortService.selectBlueSortList(blueSort);
        return getDataTable(list);
    }

    /**
     * 导出分类列表
     */
    @RequiresPermissions("sort:sort:export")
    @Log(title = "分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueSort blueSort) {
        List<BlueSort> list = blueSortService.selectBlueSortList(blueSort);
        ExcelUtil<BlueSort> util = new ExcelUtil<BlueSort>(BlueSort.class);
        util.exportExcel(response, list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @RequiresPermissions("sort:sort:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueSortService.selectBlueSortById(id));
    }

    /**
     * 新增分类
     */
    @RequiresPermissions("sort:sort:add")
    @Log(title = "分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueSort blueSort) {
        return toAjax(blueSortService.insertBlueSort(blueSort));
    }

    /**
     * 修改分类
     */
    @RequiresPermissions("sort:sort:edit")
    @Log(title = "分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueSort blueSort) {
        return toAjax(blueSortService.updateBlueSort(blueSort));
    }

    /**
     * 删除分类
     */
    @RequiresPermissions("sort:sort:remove")
    @Log(title = "分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueSortService.deleteBlueSortByIds(ids));
    }
}
