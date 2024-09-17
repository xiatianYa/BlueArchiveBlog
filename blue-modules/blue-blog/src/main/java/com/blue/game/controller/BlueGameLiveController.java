package com.blue.game.controller;

import java.util.List;
import javax.annotation.Resource;

import com.blue.common.security.annotation.RequiresLogin;
import com.blue.game.domain.vo.BiliUserDataVo;
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
import com.blue.game.domain.BlueGameLive;
import com.blue.game.service.IBlueGameLiveService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;

/**
 * 游戏直播Controller
 * 
 * @author ruoyi
 * @date 2024-09-11
 */
@RestController
@RequestMapping("/live")
public class BlueGameLiveController extends BaseController
{
    @Resource
    private IBlueGameLiveService blueGameLiveService;

    /**
     * 查询游戏直播列表
     */
    @GetMapping("/list")
    public AjaxResult list(BlueGameLive blueGameLive)
    {
        List<BiliUserDataVo> list = blueGameLiveService.selectBlueGameLiveList(blueGameLive);
        return AjaxResult.success(list);
    }

    /**
     * 获取游戏直播详细信息
     */
    @RequiresPermissions("game:live:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueGameLiveService.selectBlueGameLiveById(id));
    }

    /**
     * 新增游戏直播
     */
    @RequiresLogin
    @Log(title = "游戏直播", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueGameLive blueGameLive)
    {
        return toAjax(blueGameLiveService.insertBlueGameLive(blueGameLive));
    }

    /**
     * 修改游戏直播
     */
    @RequiresPermissions("game:live:edit")
    @Log(title = "游戏直播", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueGameLive blueGameLive)
    {
        return toAjax(blueGameLiveService.updateBlueGameLive(blueGameLive));
    }

    /**
     * 删除游戏直播
     */
    @RequiresPermissions("game:live:remove")
    @Log(title = "游戏直播", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueGameLiveService.deleteBlueGameLiveByIds(ids));
    }
}
