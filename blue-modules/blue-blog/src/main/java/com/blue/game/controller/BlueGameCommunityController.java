package com.blue.game.controller;

import java.util.List;
import java.io.IOException;
import javax.annotation.Resource;
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
import com.blue.game.domain.BlueGameCommunity;
import com.blue.game.service.IBlueGameCommunityService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 游戏社区Controller
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/community")
public class BlueGameCommunityController extends BaseController
{
    @Resource
    private IBlueGameCommunityService blueGameCommunityService;

    /**
     * 查询游戏社区列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueGameCommunity blueGameCommunity)
    {
        startPage();
        List<BlueGameCommunity> list = blueGameCommunityService.selectBlueGameCommunityList(blueGameCommunity);
        return getDataTable(list);
    }

    /**
     * 导出游戏社区列表
     */
    @RequiresPermissions("game:community:export")
    @Log(title = "游戏社区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameCommunity blueGameCommunity)
    {
        List<BlueGameCommunity> list = blueGameCommunityService.selectBlueGameCommunityList(blueGameCommunity);
        ExcelUtil<BlueGameCommunity> util = new ExcelUtil<BlueGameCommunity>(BlueGameCommunity.class);
        util.exportExcel(response, list, "游戏社区数据");
    }

    /**
     * 获取游戏社区详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameCommunityService.selectBlueGameCommunityById(id));
    }

    /**
     * 新增游戏社区
     */
    @RequiresPermissions("game:community:add")
    @Log(title = "游戏社区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameCommunity blueGameCommunity)
    {
        return toAjax(blueGameCommunityService.insertBlueGameCommunity(blueGameCommunity));
    }

    /**
     * 修改游戏社区
     */
    @RequiresPermissions("game:community:edit")
    @Log(title = "游戏社区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameCommunity blueGameCommunity)
    {
        return toAjax(blueGameCommunityService.updateBlueGameCommunity(blueGameCommunity));
    }

    /**
     * 删除游戏社区
     */
    @RequiresPermissions("game:community:remove")
    @Log(title = "游戏社区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameCommunityService.deleteBlueGameCommunityByIds(ids));
    }
}
