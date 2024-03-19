package com.blue.blog.controller;

import com.blue.blog.domain.BlueAvater;
import com.blue.blog.service.IBlueAvaterService;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户头像列存储Controller
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/avater")
public class BlueAvaterController extends BaseController
{
    @Autowired
    private IBlueAvaterService blueAvaterService;

    /**
     * 查询用户头像列存储列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueAvater blueAvater)
    {
        startPage();
        List<BlueAvater> list = blueAvaterService.selectBlueAvaterList(blueAvater);
        return getDataTable(list);
    }

    /**
     * 导出用户头像列存储列表
     */
    @RequiresPermissions("blog:avater:export")
    @Log(title = "用户头像列存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueAvater blueAvater)
    {
        List<BlueAvater> list = blueAvaterService.selectBlueAvaterList(blueAvater);
        ExcelUtil<BlueAvater> util = new ExcelUtil<BlueAvater>(BlueAvater.class);
        util.exportExcel(response, list, "用户头像列存储数据");
    }

    /**
     * 获取用户头像列存储详细信息
     */
    @RequiresPermissions("blog:avater:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueAvaterService.selectBlueAvaterById(id));
    }

    /**
     * 新增用户头像列存储
     */
    @RequiresPermissions("blog:avater:add")
    @Log(title = "用户头像列存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueAvater blueAvater)
    {
        return toAjax(blueAvaterService.insertBlueAvater(blueAvater));
    }

    /**
     * 修改用户头像列存储
     */
    @RequiresPermissions("blog:avater:edit")
    @Log(title = "用户头像列存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueAvater blueAvater)
    {
        return toAjax(blueAvaterService.updateBlueAvater(blueAvater));
    }

    /**
     * 删除用户头像列存储
     */
    @RequiresPermissions("blog:avater:remove")
    @Log(title = "用户头像列存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueAvaterService.deleteBlueAvaterByIds(ids));
    }
}
