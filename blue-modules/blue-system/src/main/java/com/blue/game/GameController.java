package com.blue.game;


import com.blue.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据统计Controller
 *
 * @author summer
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/game")
public class GameController {
    //初始化画板
    @PostMapping("/init")
    public AjaxResult init(){
        return AjaxResult.success();
    }
}
