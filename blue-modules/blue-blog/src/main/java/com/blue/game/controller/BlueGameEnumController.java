package com.blue.game.controller;

import com.blue.common.core.enums.GameModeStatus;
import com.blue.common.core.enums.GameMapTagStatus;
import com.blue.common.core.enums.GameTypeStatus;
import com.blue.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 游戏服务器模式枚举Controller
 *
 * @author xiatian
 * @date 2024-08-12
 */
@RestController
@RequestMapping("/gameEnum")
public class BlueGameEnumController {
    /**
     * 查询游戏模式枚举
     */
    @GetMapping("/mode")
    public AjaxResult mode()
    {
        Map<Long, String> map = GameModeStatus.toMap();
        return AjaxResult.success(map);
    }

    /**
     * 查询地图标签枚举
     */
    @GetMapping("/tag")
    public AjaxResult tag()
    {
        Map<Long, String> map = GameMapTagStatus.toMap();
        return AjaxResult.success(map);
    }

    /**
     * 查询地图难度
     */
    @GetMapping("/type")
    public AjaxResult type()
    {
        Map<Long, String> map = GameTypeStatus.toMap();
        return AjaxResult.success(map);
    }
}
