package com.blue.game.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.blue.game.domain.BlueGameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
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
    @Resource
    private IBlueGameServerService blueGameServerService;

    /**
     * 查询游戏服务器列表
     */
    @GetMapping("/list")
    @Cacheable("serverAllCache")
    public TableDataInfo list(BlueGameServer blueGameServer)
    {
        startPage();
        List<BlueGameServer> list = blueGameServerService.selectBlueGameServerList(blueGameServer);
        return getDataTable(list);
    }

    /**
     * 查询全部游戏服务器
     *
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(){
        List<BlueGameServer> list = blueGameServerService.listAll();
        return AjaxResult.success(list);
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
    @CacheEvict("serverAllCache")
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
    @CacheEvict("serverAllCache")
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
    @CacheEvict("serverAllCache")
    @RequiresPermissions("game:server:remove")
    @Log(title = "游戏服务器", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameServerService.deleteBlueGameServerByIds(ids));
    }

    /**
     * 搜索服务器信息
     */
    @GetMapping("/steamApi")
    public String getSteamApi(@RequestParam(required = false) String countryId){
        return blueGameServerService.getSteamApi(countryId);
    }
}
