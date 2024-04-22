package com.blue.blog.controller;

import com.blue.blog.domain.BluePixivTv;
import com.blue.blog.service.IBluePixivTvService;
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
 * 番剧信息Controller
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/tv")
public class BluePixivTvController extends BaseController
{
    @Resource
    private IBluePixivTvService bluePixivTvService;

    /**
     * 查询番剧信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BluePixivTv bluePixivTv)
    {
        startPage();
        List<BluePixivTv> list = bluePixivTvService.selectBluePixivTvList(bluePixivTv);
        return getDataTable(list);
    }

    /**
     * 导出番剧信息列表
     */
    @RequiresPermissions("blog:tv:export")
    @Log(title = "番剧信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePixivTv bluePixivTv)
    {
        List<BluePixivTv> list = bluePixivTvService.selectBluePixivTvList(bluePixivTv);
        ExcelUtil<BluePixivTv> util = new ExcelUtil<BluePixivTv>(BluePixivTv.class);
        util.exportExcel(response, list, "番剧信息数据");
    }

    /**
     * 获取番剧信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bluePixivTvService.selectBluePixivTvById(id));
    }

    /**
     * 新增番剧信息
     */
    @RequiresPermissions("blog:tv:add")
    @Log(title = "番剧信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePixivTv bluePixivTv)
    {
        return toAjax(bluePixivTvService.insertBluePixivTv(bluePixivTv));
    }

    /**
     * 修改番剧信息
     */
    @RequiresPermissions("blog:tv:edit")
    @Log(title = "番剧信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePixivTv bluePixivTv)
    {
        return toAjax(bluePixivTvService.updateBluePixivTv(bluePixivTv));
    }

    /**
     * 删除番剧信息
     */
    @RequiresPermissions("blog:tv:remove")
    @Log(title = "番剧信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bluePixivTvService.deleteBluePixivTvByIds(ids));
    }
}
