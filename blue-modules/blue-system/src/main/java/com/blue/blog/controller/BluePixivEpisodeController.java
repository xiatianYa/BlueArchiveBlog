package com.blue.blog.controller;

import com.blue.blog.domain.BluePixivEpisode;
import com.blue.blog.service.IBluePixivEpisodeService;
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
 * 番剧集存储Controller
 * 
 * @author ruoyi
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/episode")
public class BluePixivEpisodeController extends BaseController
{
    @Autowired
    private IBluePixivEpisodeService bluePixivEpisodeService;

    /**
     * 查询番剧集存储列表
     */
    @RequiresPermissions("blog:episode:list")
    @GetMapping("/list")
    public TableDataInfo list(BluePixivEpisode bluePixivEpisode)
    {
        startPage();
        List<BluePixivEpisode> list = bluePixivEpisodeService.selectBluePixivEpisodeList(bluePixivEpisode);
        return getDataTable(list);
    }

    /**
     * 导出番剧集存储列表
     */
    @RequiresPermissions("blog:episode:export")
    @Log(title = "番剧集存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePixivEpisode bluePixivEpisode)
    {
        List<BluePixivEpisode> list = bluePixivEpisodeService.selectBluePixivEpisodeList(bluePixivEpisode);
        ExcelUtil<BluePixivEpisode> util = new ExcelUtil<BluePixivEpisode>(BluePixivEpisode.class);
        util.exportExcel(response, list, "番剧集存储数据");
    }

    /**
     * 获取番剧集存储详细信息
     */
    @RequiresPermissions("blog:episode:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bluePixivEpisodeService.selectBluePixivEpisodeById(id));
    }

    /**
     * 新增番剧集存储
     */
    @RequiresPermissions("blog:episode:add")
    @Log(title = "番剧集存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePixivEpisode bluePixivEpisode)
    {
        return toAjax(bluePixivEpisodeService.insertBluePixivEpisode(bluePixivEpisode));
    }

    /**
     * 修改番剧集存储
     */
    @RequiresPermissions("blog:episode:edit")
    @Log(title = "番剧集存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePixivEpisode bluePixivEpisode)
    {
        return toAjax(bluePixivEpisodeService.updateBluePixivEpisode(bluePixivEpisode));
    }

    /**
     * 删除番剧集存储
     */
    @RequiresPermissions("blog:episode:remove")
    @Log(title = "番剧集存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bluePixivEpisodeService.deleteBluePixivEpisodeByIds(ids));
    }
}
