package com.blue.blog.controller;

import com.blue.blog.domain.BlueFriendInfo;
import com.blue.blog.service.IBlueFriendInfoService;
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
 * 友链申请信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@RestController
@RequestMapping("/friendInfo")
public class BlueFriendInfoController extends BaseController
{
    @Resource
    private IBlueFriendInfoService blueFriendInfoService;

    /**
     * 查询友链申请信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueFriendInfo blueFriendInfo)
    {
        startPage();
        List<BlueFriendInfo> list = blueFriendInfoService.selectBlueFriendInfoList(blueFriendInfo);
        return getDataTable(list);
    }

    /**
     * 导出友链申请信息列表
     */
    @RequiresPermissions("blog:info:export")
    @Log(title = "友链申请信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueFriendInfo blueFriendInfo)
    {
        List<BlueFriendInfo> list = blueFriendInfoService.selectBlueFriendInfoList(blueFriendInfo);
        ExcelUtil<BlueFriendInfo> util = new ExcelUtil<BlueFriendInfo>(BlueFriendInfo.class);
        util.exportExcel(response, list, "友链申请信息数据");
    }

    /**
     * 获取友链申请信息详细信息
     */
    @RequiresPermissions("blog:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueFriendInfoService.selectBlueFriendInfoById(id));
    }

    /**
     * 新增友链申请信息
     */
    @RequiresPermissions("blog:info:add")
    @Log(title = "友链申请信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueFriendInfo blueFriendInfo)
    {
        return toAjax(blueFriendInfoService.insertBlueFriendInfo(blueFriendInfo));
    }

    /**
     * 修改友链申请信息
     */
    @RequiresPermissions("blog:info:edit")
    @Log(title = "友链申请信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueFriendInfo blueFriendInfo)
    {
        return toAjax(blueFriendInfoService.updateBlueFriendInfo(blueFriendInfo));
    }

    /**
     * 删除友链申请信息
     */
    @RequiresPermissions("blog:info:remove")
    @Log(title = "友链申请信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueFriendInfoService.deleteBlueFriendInfoByIds(ids));
    }
}
