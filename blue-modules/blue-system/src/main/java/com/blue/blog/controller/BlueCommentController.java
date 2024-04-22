package com.blue.blog.controller;

import com.blue.blog.domain.BlueComment;
import com.blue.blog.service.IBlueCommentService;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 评论Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/comment")
public class BlueCommentController extends BaseController
{
    @Resource
    private IBlueCommentService blueCommentService;

    /**
     * 查询评论列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAll(BlueComment blueComment)
    {
        startPage();
        List<BlueComment> list = blueCommentService.selectBlueCommentListAll(blueComment);
        return getDataTable(list);
    }

    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueComment blueComment)
    {
        startPage();
        List<BlueComment> list = blueCommentService.selectBlueCommentList(blueComment);
        return getDataTable(list);
    }

    /**
     * 导出评论列表
     */
    @RequiresPermissions("blog:comment:export")
    @Log(title = "评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueComment blueComment)
    {
        List<BlueComment> list = blueCommentService.selectBlueCommentList(blueComment);
        ExcelUtil<BlueComment> util = new ExcelUtil<BlueComment>(BlueComment.class);
        util.exportExcel(response, list, "评论数据");
    }

    /**
     * 获取评论详细信息
     */
    @RequiresPermissions("blog:comment:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueCommentService.selectBlueCommentById(id));
    }

    /**
     * 新增评论
     */
    @RequiresPermissions("blog:comment:add")
    @Log(title = "评论", businessType = BusinessType.INSERT)
    @PostMapping
        public AjaxResult add(@RequestBody BlueComment blueComment)
    {
        return toAjax(blueCommentService.insertBlueComment(blueComment));
    }

    /**
     * 修改评论
     */
    @RequiresPermissions("blog:comment:edit")
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueComment blueComment)
    {
        return toAjax(blueCommentService.updateBlueComment(blueComment));
    }

    /**
     * 删除评论
     */
    @RequiresPermissions("blog:comment:remove")
    @Log(title = "评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueCommentService.deleteBlueCommentByIds(ids));
    }
}
