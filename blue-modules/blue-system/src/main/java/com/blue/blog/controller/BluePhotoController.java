package com.blue.blog.controller;

import com.blue.blog.domain.BluePhoto;
import com.blue.blog.service.IBluePhotoService;
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
 * 相册Controller
 * 
 * @author ruoyi
 * @date 2024-03-19
 */
@RestController
@RequestMapping("/photo")
public class BluePhotoController extends BaseController
{
    @Autowired
    private IBluePhotoService bluePhotoService;


    /**
     * 查询用户相册列表
     */
    @GetMapping("/listByUser")
    public AjaxResult listByUser()
    {
        List<BluePhoto> list = bluePhotoService.selectBluePhotoListByUser();
        return AjaxResult.success(list);
    }
    /**
     * 查询相册列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BluePhoto bluePhoto)
    {
        startPage();
        List<BluePhoto> list = bluePhotoService.selectBluePhotoList(bluePhoto);
        return getDataTable(list);
    }

    /**
     * 导出相册列表
     */
    @RequiresPermissions("blog:photo:export")
    @Log(title = "相册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePhoto bluePhoto)
    {
        List<BluePhoto> list = bluePhotoService.selectBluePhotoList(bluePhoto);
        ExcelUtil<BluePhoto> util = new ExcelUtil<BluePhoto>(BluePhoto.class);
        util.exportExcel(response, list, "相册数据");
    }

    /**
     * 获取相册详细信息
     */
    @RequiresPermissions("blog:photo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bluePhotoService.selectBluePhotoById(id));
    }

    /**
     * 新增相册
     */
    @RequiresPermissions("blog:photo:add")
    @Log(title = "相册", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePhoto bluePhoto)
    {
        return toAjax(bluePhotoService.insertBluePhoto(bluePhoto));
    }

    /**
     * 修改相册
     */
    @RequiresPermissions("blog:photo:edit")
    @Log(title = "相册", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePhoto bluePhoto)
    {
        return toAjax(bluePhotoService.updateBluePhoto(bluePhoto));
    }

    /**
     * 删除相册
     */
    @RequiresPermissions("blog:photo:remove")
    @Log(title = "相册", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bluePhotoService.deleteBluePhotoByIds(ids));
    }
}
