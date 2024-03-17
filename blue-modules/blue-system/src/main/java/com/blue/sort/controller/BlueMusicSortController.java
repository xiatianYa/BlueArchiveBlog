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
import com.blue.sort.domain.BlueMusicSort;
import com.blue.sort.service.IBlueMusicSortService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 音乐分类Controller
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/musicsort")
public class BlueMusicSortController extends BaseController {
    @Autowired
    private IBlueMusicSortService blueMusicSortService;

    /**
     * 查询音乐分类列表
     */
    @RequiresPermissions("system:sort:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueMusicSort blueMusicSort) {
        startPage();
        List<BlueMusicSort> list = blueMusicSortService.selectBlueMusicSortList(blueMusicSort);
        return getDataTable(list);
    }

    /**
     * 导出音乐分类列表
     */
    @RequiresPermissions("system:sort:export")
    @Log(title = "音乐分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueMusicSort blueMusicSort) {
        List<BlueMusicSort> list = blueMusicSortService.selectBlueMusicSortList(blueMusicSort);
        ExcelUtil<BlueMusicSort> util = new ExcelUtil<BlueMusicSort>(BlueMusicSort.class);
        util.exportExcel(response, list, "音乐分类数据");
    }

    /**
     * 获取音乐分类详细信息
     */
    @RequiresPermissions("system:sort:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueMusicSortService.selectBlueMusicSortById(id));
    }

    /**
     * 新增音乐分类
     */
    @RequiresPermissions("system:sort:add")
    @Log(title = "音乐分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueMusicSort blueMusicSort) {
        return toAjax(blueMusicSortService.insertBlueMusicSort(blueMusicSort));
    }

    /**
     * 修改音乐分类
     */
    @RequiresPermissions("system:sort:edit")
    @Log(title = "音乐分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueMusicSort blueMusicSort) {
        return toAjax(blueMusicSortService.updateBlueMusicSort(blueMusicSort));
    }

    /**
     * 删除音乐分类
     */
    @RequiresPermissions("system:sort:remove")
    @Log(title = "音乐分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueMusicSortService.deleteBlueMusicSortByIds(ids));
    }
}
