package com.blue.game.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.blue.common.core.domain.Server;
import com.blue.common.core.utils.udp.GameServerUtil;
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
import com.blue.game.domain.BlueGameServer;
import com.blue.game.service.IBlueGameServerService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 游戏服务器Controller
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@RestController
@RequestMapping("/server")
public class BlueGameServerController extends BaseController
{
    @Autowired
    private IBlueGameServerService blueGameServerService;

    /**
     * 查询游戏服务器列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueGameServer blueGameServer)
    {
        startPage();
        List<BlueGameServer> list = blueGameServerService.selectBlueGameServerList(blueGameServer);
        return getDataTable(list);
    }

    /**
     * 导出游戏服务器列表
     */
    @RequiresPermissions("game:server:export")
    @Log(title = "游戏服务器", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueGameServer blueGameServer)
    {
        List<BlueGameServer> list = blueGameServerService.selectBlueGameServerList(blueGameServer);
        ExcelUtil<BlueGameServer> util = new ExcelUtil<BlueGameServer>(BlueGameServer.class);
        util.exportExcel(response, list, "游戏服务器数据");
    }

    /**
     * 获取游戏服务器详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameServerService.selectBlueGameServerById(id));
    }

    /**
     * 新增游戏服务器
     */
    @RequiresPermissions("game:server:add")
    @Log(title = "游戏服务器", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameServer blueGameServer)
    {
        return toAjax(blueGameServerService.insertBlueGameServer(blueGameServer));
    }

    /**
     * 修改游戏服务器
     */
    @RequiresPermissions("game:server:edit")
    @Log(title = "游戏服务器", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameServer blueGameServer)
    {
        return toAjax(blueGameServerService.updateBlueGameServer(blueGameServer));
    }

    /**
     * 删除游戏服务器
     */
    @RequiresPermissions("game:server:remove")
    @Log(title = "游戏服务器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameServerService.deleteBlueGameServerByIds(ids));
    }
}
