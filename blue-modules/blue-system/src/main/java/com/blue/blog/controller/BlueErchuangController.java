package com.blue.blog.controller;

import com.blue.blog.domain.BlueErchuang;
import com.blue.blog.service.IBlueErchuangService;
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
 * 二创信息Controller
 * 
 * @author ruoyi
 * @date 2024-04-02
 */
@RestController
@RequestMapping("/erchuang")
public class BlueErchuangController extends BaseController
{
    @Autowired
    private IBlueErchuangService blueErchuangService;

    /**
     * 查询用户的二创信息列表
     */
    @RequiresPermissions("blog:erchuang:query")
    @GetMapping("/listByUser")
    public AjaxResult listByUser()
    {
        List<BlueErchuang> list = blueErchuangService.selectBlueErchuangListByUser();
        return AjaxResult.success(list);
    }

    /**
     * 查询二创信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueErchuang blueErchuang)
    {
        startPage();
        List<BlueErchuang> list = blueErchuangService.selectBlueErchuangList(blueErchuang);
        return getDataTable(list);
    }

    /**
     * 导出二创信息列表
     */
    @RequiresPermissions("blog:erchuang:export")
    @Log(title = "二创信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueErchuang blueErchuang)
    {
        List<BlueErchuang> list = blueErchuangService.selectBlueErchuangList(blueErchuang);
        ExcelUtil<BlueErchuang> util = new ExcelUtil<BlueErchuang>(BlueErchuang.class);
        util.exportExcel(response, list, "二创信息数据");
    }

    /**
     * 获取二创信息详细信息
     */
    @RequiresPermissions("blog:erchuang:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueErchuangService.selectBlueErchuangById(id));
    }

    /**
     * 新增二创信息
     */
    @RequiresPermissions("blog:erchuang:add")
    @Log(title = "二创信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueErchuang blueErchuang)
    {
        return toAjax(blueErchuangService.insertBlueErchuang(blueErchuang));
    }

    /**
     * 修改二创信息
     */
    @RequiresPermissions("blog:erchuang:edit")
    @Log(title = "二创信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueErchuang blueErchuang)
    {
        return toAjax(blueErchuangService.updateBlueErchuang(blueErchuang));
    }

    /**
     * 删除二创信息
     */
    @RequiresPermissions("blog:erchuang:remove")
    @Log(title = "二创信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueErchuangService.deleteBlueErchuangByIds(ids));
    }
}
