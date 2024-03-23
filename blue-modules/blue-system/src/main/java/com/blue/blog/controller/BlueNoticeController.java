package com.blue.blog.controller;

import com.blue.blog.domain.BlueNotice;
import com.blue.blog.service.IBlueNoticeService;
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
 * 公告信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@RestController
@RequestMapping("/noticeInfo")
public class BlueNoticeController extends BaseController
{
    @Autowired
    private IBlueNoticeService blueNoticeService;

    /**
     * 查询公告信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueNotice blueNotice)
    {
        startPage();
        List<BlueNotice> list = blueNoticeService.selectBlueNoticeList(blueNotice);
        return getDataTable(list);
    }

    /**
     * 导出公告信息列表
     */
    @RequiresPermissions("blog:notice:export")
    @Log(title = "公告信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueNotice blueNotice)
    {
        List<BlueNotice> list = blueNoticeService.selectBlueNoticeList(blueNotice);
        ExcelUtil<BlueNotice> util = new ExcelUtil<BlueNotice>(BlueNotice.class);
        util.exportExcel(response, list, "公告信息数据");
    }

    /**
     * 获取公告信息详细信息
     */
    @RequiresPermissions("blog:notice:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueNoticeService.selectBlueNoticeById(id));
    }

    /**
     * 新增公告信息
     */
    @RequiresPermissions("blog:notice:add")
    @Log(title = "公告信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueNotice blueNotice)
    {
        return toAjax(blueNoticeService.insertBlueNotice(blueNotice));
    }

    /**
     * 修改公告信息
     */
    @RequiresPermissions("blog:notice:edit")
    @Log(title = "公告信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueNotice blueNotice)
    {
        return toAjax(blueNoticeService.updateBlueNotice(blueNotice));
    }

    /**
     * 删除公告信息
     */
    @RequiresPermissions("blog:notice:remove")
    @Log(title = "公告信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueNoticeService.deleteBlueNoticeByIds(ids));
    }
}
