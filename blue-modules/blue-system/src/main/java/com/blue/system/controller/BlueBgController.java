package com.blue.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.system.domain.BlueBg;
import com.blue.system.service.IBlueBgService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 网站背景资源信息Controller
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/bg")
public class BlueBgController extends BaseController {
    @Autowired
    private IBlueBgService blueBgService;

    /**
     * 查询网站背景资源信息列表
     */
    @RequiresPermissions("system:bg:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueBg blueBg) {
        startPage();
        List<BlueBg> list = blueBgService.selectBlueBgList(blueBg);
        return getDataTable(list);
    }

    /**
     * 导出网站背景资源信息列表
     */
    @RequiresPermissions("system:bg:export")
    @Log(title = "网站背景资源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueBg blueBg) {
        List<BlueBg> list = blueBgService.selectBlueBgList(blueBg);
        ExcelUtil<BlueBg> util = new ExcelUtil<BlueBg>(BlueBg.class);
        util.exportExcel(response, list, "网站背景资源信息数据");
    }

    /**
     * 获取网站背景资源信息详细信息
     */
    @RequiresPermissions("system:bg:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueBgService.selectBlueBgById(id));
    }

    /**
     * 新增网站背景资源信息
     */
    @RequiresPermissions("system:bg:add")
    @Log(title = "网站背景资源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueBg blueBg) {
        return toAjax(blueBgService.insertBlueBg(blueBg));
    }

    /**
     * 修改网站背景资源信息
     */
    @RequiresPermissions("system:bg:edit")
    @Log(title = "网站背景资源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueBg blueBg) {
        return toAjax(blueBgService.updateBlueBg(blueBg));
    }

    /**
     * 删除网站背景资源信息
     */
    @RequiresPermissions("system:bg:remove")
    @Log(title = "网站背景资源信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueBgService.deleteBlueBgByIds(ids));
    }
}
