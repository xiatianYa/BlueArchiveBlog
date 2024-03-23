package com.blue.sort.controller;

import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.service.IBlueArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章标签关联Controller
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@RestController
@RequestMapping("/sortTag")
public class BlueArticleTagController extends BaseController
{
    @Autowired
    private IBlueArticleTagService blueArticleTagService;

    /**
     * 查询文章标签关联列表
     */
    @RequiresPermissions("blog:tag:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueArticleTag blueArticleTag)
    {
        startPage();
        List<BlueArticleTag> list = blueArticleTagService.selectBlueArticleTagList(blueArticleTag);
        return getDataTable(list);
    }

    /**
     * 导出文章标签关联列表
     */
    @RequiresPermissions("blog:tag:export")
    @Log(title = "文章标签关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueArticleTag blueArticleTag)
    {
        List<BlueArticleTag> list = blueArticleTagService.selectBlueArticleTagList(blueArticleTag);
        ExcelUtil<BlueArticleTag> util = new ExcelUtil<BlueArticleTag>(BlueArticleTag.class);
        util.exportExcel(response, list, "文章标签关联数据");
    }

    /**
     * 获取文章标签关联详细信息
     */
    @RequiresPermissions("blog:tag:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueArticleTagService.selectBlueArticleTagById(id));
    }

    /**
     * 新增文章标签关联
     */
    @RequiresPermissions("blog:tag:add")
    @Log(title = "文章标签关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueArticleTag blueArticleTag)
    {
        return toAjax(blueArticleTagService.insertBlueArticleTag(blueArticleTag));
    }

    /**
     * 修改文章标签关联
     */
    @RequiresPermissions("blog:tag:edit")
    @Log(title = "文章标签关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueArticleTag blueArticleTag)
    {
        return toAjax(blueArticleTagService.updateBlueArticleTag(blueArticleTag));
    }

    /**
     * 删除文章标签关联
     */
    @RequiresPermissions("blog:tag:remove")
    @Log(title = "文章标签关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueArticleTagService.deleteBlueArticleTagByIds(ids));
    }
}
