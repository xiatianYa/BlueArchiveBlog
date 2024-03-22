package com.blue.blog.controller;

import com.blue.blog.domain.BlueFriend;
import com.blue.blog.service.IBlueFriendService;
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
 * 友情链接Controller
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
@RestController
@RequestMapping("/friend")
public class BlueFriendController extends BaseController
{
    @Autowired
    private IBlueFriendService blueFriendService;

    /**
     * 查询友情链接列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueFriend blueFriend)
    {
        startPage();
        List<BlueFriend> list = blueFriendService.selectBlueFriendList(blueFriend);
        return getDataTable(list);
    }

    /**
     * 导出友情链接列表
     */
    @RequiresPermissions("system:friend:export")
    @Log(title = "友情链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueFriend blueFriend)
    {
        List<BlueFriend> list = blueFriendService.selectBlueFriendList(blueFriend);
        ExcelUtil<BlueFriend> util = new ExcelUtil<BlueFriend>(BlueFriend.class);
        util.exportExcel(response, list, "友情链接数据");
    }

    /**
     * 获取友情链接详细信息
     */
    @RequiresPermissions("system:friend:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueFriendService.selectBlueFriendById(id));
    }

    /**
     * 新增友情链接
     */
    @Log(title = "友情链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueFriend blueFriend)
    {
        return toAjax(blueFriendService.insertBlueFriend(blueFriend));
    }

    /**
     * 修改友情链接
     */
    @RequiresPermissions("system:friend:edit")
    @Log(title = "友情链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueFriend blueFriend)
    {
        return toAjax(blueFriendService.updateBlueFriend(blueFriend));
    }

    /**
     * 删除友情链接
     */
    @RequiresPermissions("system:friend:remove")
    @Log(title = "友情链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueFriendService.deleteBlueFriendByIds(ids));
    }
}
