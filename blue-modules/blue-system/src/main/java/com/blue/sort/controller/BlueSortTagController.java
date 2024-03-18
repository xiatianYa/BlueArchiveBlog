package com.blue.sort.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.sort.domain.BlueSortTag;
import com.blue.sort.service.IBlueSortTagService;
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
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 标签Controller
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/tag")
public class BlueSortTagController extends BaseController
{
    @Autowired
    private IBlueSortTagService blueSortTagService;

    /**
     * 查询标签列表
     */
    @RequiresPermissions("system:tag:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueSortTag blueSortTag)
    {
        startPage();
        List<BlueSortTag> list = blueSortTagService.selectBlueSortTagList(blueSortTag);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @RequiresPermissions("system:tag:export")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueSortTag blueSortTag)
    {
        List<BlueSortTag> list = blueSortTagService.selectBlueSortTagList(blueSortTag);
        ExcelUtil<BlueSortTag> util = new ExcelUtil<BlueSortTag>(BlueSortTag.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @RequiresPermissions("system:tag:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueSortTagService.selectBlueSortTagById(id));
    }

    /**
     * 新增标签
     */
    @RequiresPermissions("system:tag:add")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueSortTag blueSortTag)
    {
        return toAjax(blueSortTagService.insertBlueSortTag(blueSortTag));
    }

    /**
     * 修改标签
     */
    @RequiresPermissions("system:tag:edit")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueSortTag blueSortTag)
    {
        return toAjax(blueSortTagService.updateBlueSortTag(blueSortTag));
    }

    /**
     * 删除标签
     */
    @RequiresPermissions("system:tag:remove")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueSortTagService.deleteBlueSortTagByIds(ids));
    }
}
