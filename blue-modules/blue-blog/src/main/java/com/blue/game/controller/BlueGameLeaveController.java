package com.blue.game.controller;

import java.util.List;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.blue.game.domain.dto.BlueGameLeaveResDto;
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
import com.blue.game.domain.BlueGameLeave;
import com.blue.game.service.IBlueGameLeaveService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 游戏留言Controller
 *
 * @author ruoyi
 * @date 2024-09-17
 */
@RestController
@RequestMapping("/leave")
public class BlueGameLeaveController extends BaseController {
    @Resource
    private IBlueGameLeaveService blueGameLeaveService;

    /**
     * 查询游戏留言列表
     */
    @RequiresPermissions("game:leave:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueGameLeave blueGameLeave) {
        startPage();
        List<BlueGameLeave> list = blueGameLeaveService.selectBlueGameLeaveList(blueGameLeave);
        return getDataTable(list);
    }

    /**
     * 查询游戏留言列表，联表查询用户信息
     */
    @GetMapping("/list/v2")
    public TableDataInfo listV2(BlueGameLeave blueGameLeave) {
        startPage();
        List<BlueGameLeaveResDto> list = blueGameLeaveService.selectBlueGameLeaveListV2(blueGameLeave);
        return getDataTable(list);
    }

    /**
     * 导出游戏留言列表
     */
    @RequiresPermissions("game:leave:export")
    @Log(title = "游戏留言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameLeave blueGameLeave) {
        List<BlueGameLeave> list = blueGameLeaveService.selectBlueGameLeaveList(blueGameLeave);
        ExcelUtil<BlueGameLeave> util = new ExcelUtil<BlueGameLeave>(BlueGameLeave.class);
        util.exportExcel(response, list, "游戏留言数据");
    }

    /**
     * 获取游戏留言详细信息
     */
    @RequiresPermissions("game:leave:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueGameLeaveService.selectBlueGameLeaveById(id));
    }

    /**
     * 新增游戏留言
     */
    @RequiresPermissions("game:leave:add")
    @Log(title = "游戏留言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameLeave blueGameLeave) {
        return toAjax(blueGameLeaveService.insertBlueGameLeave(blueGameLeave));
    }

    /**
     * 修改游戏留言
     */
    @RequiresPermissions("game:leave:edit")
    @Log(title = "游戏留言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameLeave blueGameLeave) {
        return toAjax(blueGameLeaveService.updateBlueGameLeave(blueGameLeave));
    }

    /**
     * 删除游戏留言
     */
    @RequiresPermissions("game:leave:remove")
    @Log(title = "游戏留言", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueGameLeaveService.deleteBlueGameLeaveByIds(ids));
    }
}
