package com.blue.blog.controller;

import com.blue.blog.domain.BlueWebsite;
import com.blue.blog.service.IBlueWebsiteService;
import com.blue.common.core.utils.StringUtils;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 网站信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@RestController
@RequestMapping("/websiteInfo")
public class BlueWebsiteController extends BaseController
{
    @Autowired
    private IBlueWebsiteService blueWebsiteService;

    /**
     * 查询网站信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueWebsite blueWebsite)
    {
        startPage();
        List<BlueWebsite> list = blueWebsiteService.selectBlueWebsiteList(blueWebsite);
        return getDataTable(list);
    }

    /**
     * 导出网站信息列表
     */
    @RequiresPermissions("blog:website:export")
    @Log(title = "网站信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueWebsite blueWebsite)
    {
        List<BlueWebsite> list = blueWebsiteService.selectBlueWebsiteList(blueWebsite);
        ExcelUtil<BlueWebsite> util = new ExcelUtil<BlueWebsite>(BlueWebsite.class);
        util.exportExcel(response, list, "网站信息数据");
    }

    /**
     * 获取网站信息详细信息
     */
    @RequiresPermissions("blog:website:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueWebsiteService.selectBlueWebsiteById(id));
    }

    /**
     * 新增网站信息
     */
    @RequiresPermissions("blog:website:add")
    @Log(title = "网站信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueWebsite blueWebsite)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueWebsite.setCreateBy(String.valueOf(userId));
        }
        return toAjax(blueWebsiteService.insertBlueWebsite(blueWebsite));
    }

    /**
     * 修改网站信息
     */
    @RequiresPermissions("blog:website:edit")
    @Log(title = "网站信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueWebsite blueWebsite)
    {
        Long userId = SecurityUtils.getUserId();
        if (StringUtils.isNotNull(userId)){
            blueWebsite.setUpdateBy(String.valueOf(userId));
        }
        return toAjax(blueWebsiteService.updateBlueWebsite(blueWebsite));
    }

    /**
     * 删除网站信息
     */
    @RequiresPermissions("blog:website:remove")
    @Log(title = "网站信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueWebsiteService.deleteBlueWebsiteByIds(ids));
    }
}
