package com.blue.blog.controller;

import com.blue.blog.entry.dao.BlueProgramTool;
import com.blue.blog.entry.dto.BlueProgramToolBySortDTO;
import com.blue.blog.service.IBlueProgramToolService;
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
 * 编程工具Controller
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@RestController
@RequestMapping("/tool")
public class BlueProgramToolController extends BaseController
{
    @Resource
    private IBlueProgramToolService blueProgramToolService;

    /**
     * 查询编程工具列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueProgramTool blueProgramTool)
    {
        startPage();
        List<BlueProgramTool> list = blueProgramToolService.selectBlueProgramToolList(blueProgramTool);
        return getDataTable(list);
    }

    /**
     * 查询全部编程工具列表 根据分类
     */
    @GetMapping("/listBySort")
    public TableDataInfo listBySortId(BlueProgramTool blueProgramTool)
    {
        startPage();
        List<BlueProgramToolBySortDTO> list = blueProgramToolService.selectBlueProgramToolListBySortId(blueProgramTool);
        return getDataTable(list);
    }

    /**
     * 导出编程工具列表
     */
    @RequiresPermissions("blog:tool:export")
    @Log(title = "编程工具", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueProgramTool blueProgramTool)
    {
        List<BlueProgramTool> list = blueProgramToolService.selectBlueProgramToolList(blueProgramTool);
        ExcelUtil<BlueProgramTool> util = new ExcelUtil<BlueProgramTool>(BlueProgramTool.class);
        util.exportExcel(response, list, "编程工具数据");
    }

    /**
     * 获取编程工具详细信息
     */
    @RequiresPermissions("blog:tool:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueProgramToolService.selectBlueProgramToolById(id));
    }

    /**
     * 新增编程工具
     */
    @RequiresPermissions("blog:tool:add")
    @Log(title = "编程工具", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueProgramTool blueProgramTool)
    {
        return toAjax(blueProgramToolService.insertBlueProgramTool(blueProgramTool));
    }

    /**
     * 修改编程工具
     */
    @RequiresPermissions("blog:tool:edit")
    @Log(title = "编程工具", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueProgramTool blueProgramTool)
    {
        return toAjax(blueProgramToolService.updateBlueProgramTool(blueProgramTool));
    }

    /**
     * 删除编程工具
     */
    @RequiresPermissions("blog:tool:remove")
    @Log(title = "编程工具", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueProgramToolService.deleteBlueProgramToolByIds(ids));
    }
}
