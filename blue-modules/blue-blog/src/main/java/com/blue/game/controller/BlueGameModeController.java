package com.blue.game.controller;

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
import com.blue.game.domain.BlueGameMode;
import com.blue.game.service.IBlueGameModeService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 游戏模式Controller
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/mode")
public class BlueGameModeController extends BaseController
{
    @Autowired
    private IBlueGameModeService blueGameModeService;

    /**
     * 查询游戏模式列表
     */
    @RequiresPermissions("game:mode:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueGameMode blueGameMode)
    {
        startPage();
        List<BlueGameMode> list = blueGameModeService.selectBlueGameModeList(blueGameMode);
        return getDataTable(list);
    }

    /**
     * 导出游戏模式列表
     */
    @RequiresPermissions("game:mode:export")
    @Log(title = "游戏模式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameMode blueGameMode)
    {
        List<BlueGameMode> list = blueGameModeService.selectBlueGameModeList(blueGameMode);
        ExcelUtil<BlueGameMode> util = new ExcelUtil<BlueGameMode>(BlueGameMode.class);
        util.exportExcel(response, list, "游戏模式数据");
    }

    /**
     * 获取游戏模式详细信息
     */
    @RequiresPermissions("game:mode:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameModeService.selectBlueGameModeById(id));
    }

    /**
     * 新增游戏模式
     */
    @RequiresPermissions("game:mode:add")
    @Log(title = "游戏模式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameMode blueGameMode)
    {
        return toAjax(blueGameModeService.insertBlueGameMode(blueGameMode));
    }

    /**
     * 修改游戏模式
     */
    @RequiresPermissions("game:mode:edit")
    @Log(title = "游戏模式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameMode blueGameMode)
    {
        return toAjax(blueGameModeService.updateBlueGameMode(blueGameMode));
    }

    /**
     * 删除游戏模式
     */
    @RequiresPermissions("game:mode:remove")
    @Log(title = "游戏模式", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameModeService.deleteBlueGameModeByIds(ids));
    }
}
