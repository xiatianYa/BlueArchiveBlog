package com.blue.sort.controller;

import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.sort.domain.BluePixivType;
import com.blue.sort.service.IBluePixivTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 番剧类型Controller
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/pixiv/type")
public class BluePixivTypeController extends BaseController
{
    @Autowired
    private IBluePixivTypeService bluePixivTypeService;

    /**
     * 查询番剧类型列表
     */
    @RequiresPermissions("sort:type:list")
    @GetMapping("/list")
    public TableDataInfo list(BluePixivType bluePixivType)
    {
        startPage();
        List<BluePixivType> list = bluePixivTypeService.selectBluePixivTypeList(bluePixivType);
        return getDataTable(list);
    }

    /**
     * 导出番剧类型列表
     */
    @RequiresPermissions("sort:type:export")
    @Log(title = "番剧类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePixivType bluePixivType)
    {
        List<BluePixivType> list = bluePixivTypeService.selectBluePixivTypeList(bluePixivType);
        ExcelUtil<BluePixivType> util = new ExcelUtil<BluePixivType>(BluePixivType.class);
        util.exportExcel(response, list, "番剧类型数据");
    }

    /**
     * 获取番剧类型详细信息
     */
    @RequiresPermissions("sort:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bluePixivTypeService.selectBluePixivTypeById(id));
    }

    /**
     * 新增番剧类型
     */
    @RequiresPermissions("sort:type:add")
    @Log(title = "番剧类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePixivType bluePixivType)
    {
        return toAjax(bluePixivTypeService.insertBluePixivType(bluePixivType));
    }

    /**
     * 修改番剧类型
     */
    @RequiresPermissions("sort:type:edit")
    @Log(title = "番剧类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePixivType bluePixivType)
    {
        return toAjax(bluePixivTypeService.updateBluePixivType(bluePixivType));
    }

    /**
     * 删除番剧类型
     */
    @RequiresPermissions("sort:type:remove")
    @Log(title = "番剧类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bluePixivTypeService.deleteBluePixivTypeByIds(ids));
    }
}
