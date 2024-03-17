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
import com.blue.sort.domain.BluePhotoSort;
import com.blue.sort.service.IBluePhotoSortService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 相册分类信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/photosort")
public class BluePhotoSortController extends BaseController {
    @Autowired
    private IBluePhotoSortService bluePhotoSortService;

    /**
     * 查询相册分类信息列表
     */
    @RequiresPermissions("sort:photo:list")
    @GetMapping("/list")
    public TableDataInfo list(BluePhotoSort bluePhotoSort) {
        startPage();
        List<BluePhotoSort> list = bluePhotoSortService.selectBluePhotoSortList(bluePhotoSort);
        return getDataTable(list);
    }

    /**
     * 导出相册分类信息列表
     */
    @RequiresPermissions("sort:photo:export")
    @Log(title = "相册分类信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePhotoSort bluePhotoSort) {
        List<BluePhotoSort> list = bluePhotoSortService.selectBluePhotoSortList(bluePhotoSort);
        ExcelUtil<BluePhotoSort> util = new ExcelUtil<BluePhotoSort>(BluePhotoSort.class);
        util.exportExcel(response, list, "相册分类信息数据");
    }

    /**
     * 获取相册分类信息详细信息
     */
    @RequiresPermissions("sort:photo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(bluePhotoSortService.selectBluePhotoSortById(id));
    }

    /**
     * 新增相册分类信息
     */
    @RequiresPermissions("sort:photo:add")
    @Log(title = "相册分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePhotoSort bluePhotoSort) {
        return toAjax(bluePhotoSortService.insertBluePhotoSort(bluePhotoSort));
    }

    /**
     * 修改相册分类信息
     */
    @RequiresPermissions("sort:photo:edit")
    @Log(title = "相册分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePhotoSort bluePhotoSort) {
        return toAjax(bluePhotoSortService.updateBluePhotoSort(bluePhotoSort));
    }

    /**
     * 删除相册分类信息
     */
    @RequiresPermissions("sort:photo:remove")
    @Log(title = "相册分类信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bluePhotoSortService.deleteBluePhotoSortByIds(ids));
    }
}
