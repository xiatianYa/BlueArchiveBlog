package com.blue.blog.controller;

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
import com.blue.blog.domain.BlueMusic;
import com.blue.blog.service.IBlueMusicService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 音乐Controller
 *
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/music")
public class BlueMusicController extends BaseController {
    @Autowired
    private IBlueMusicService blueMusicService;

    /**
     * 查询音乐列表
     */
    @RequiresPermissions("system:music:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueMusic blueMusic) {
        startPage();
        List<BlueMusic> list = blueMusicService.selectBlueMusicList(blueMusic);
        return getDataTable(list);
    }

    /**
     * 导出音乐列表
     */
    @RequiresPermissions("system:music:export")
    @Log(title = "音乐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueMusic blueMusic) {
        List<BlueMusic> list = blueMusicService.selectBlueMusicList(blueMusic);
        ExcelUtil<BlueMusic> util = new ExcelUtil<BlueMusic>(BlueMusic.class);
        util.exportExcel(response, list, "音乐数据");
    }

    /**
     * 获取音乐详细信息
     */
    @RequiresPermissions("system:music:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueMusicService.selectBlueMusicById(id));
    }

    /**
     * 新增音乐
     */
    @RequiresPermissions("system:music:add")
    @Log(title = "音乐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueMusic blueMusic) {
        return toAjax(blueMusicService.insertBlueMusic(blueMusic));
    }

    /**
     * 修改音乐
     */
    @RequiresPermissions("system:music:edit")
    @Log(title = "音乐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueMusic blueMusic) {
        return toAjax(blueMusicService.updateBlueMusic(blueMusic));
    }

    /**
     * 删除音乐
     */
    @RequiresPermissions("system:music:remove")
    @Log(title = "音乐", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueMusicService.deleteBlueMusicByIds(ids));
    }
}
