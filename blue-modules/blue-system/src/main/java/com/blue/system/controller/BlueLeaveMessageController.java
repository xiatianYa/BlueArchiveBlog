package com.blue.system.controller;

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
import com.blue.system.domain.BlueLeaveMessage;
import com.blue.system.service.IBlueLeaveMessageService;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.page.TableDataInfo;

/**
 * 弹幕Controller
 * 
 * @author ruoyi
 * @date 2024-03-17
 */
@RestController
@RequestMapping("/message")
public class BlueLeaveMessageController extends BaseController {
    @Autowired
    private IBlueLeaveMessageService blueLeaveMessageService;

    /**
     * 查询弹幕列表
     */
    @RequiresPermissions("system:message:list")
    @GetMapping("/list")
    public TableDataInfo list(BlueLeaveMessage blueLeaveMessage) {
        startPage();
        List<BlueLeaveMessage> list = blueLeaveMessageService.selectBlueLeaveMessageList(blueLeaveMessage);
        return getDataTable(list);
    }

    /**
     * 导出弹幕列表
     */
    @RequiresPermissions("system:message:export")
    @Log(title = "弹幕", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueLeaveMessage blueLeaveMessage) {
        List<BlueLeaveMessage> list = blueLeaveMessageService.selectBlueLeaveMessageList(blueLeaveMessage);
        ExcelUtil<BlueLeaveMessage> util = new ExcelUtil<BlueLeaveMessage>(BlueLeaveMessage.class);
        util.exportExcel(response, list, "弹幕数据");
    }

    /**
     * 获取弹幕详细信息
     */
    @RequiresPermissions("system:message:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(blueLeaveMessageService.selectBlueLeaveMessageById(id));
    }

    /**
     * 新增弹幕
     */
    @Log(title = "弹幕", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueLeaveMessage blueLeaveMessage) {
        return toAjax(blueLeaveMessageService.insertBlueLeaveMessage(blueLeaveMessage));
    }

    /**
     * 修改弹幕
     */
    @RequiresPermissions("system:message:edit")
    @Log(title = "弹幕", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueLeaveMessage blueLeaveMessage) {
        return toAjax(blueLeaveMessageService.updateBlueLeaveMessage(blueLeaveMessage));
    }

    /**
     * 删除弹幕
     */
    @RequiresPermissions("system:message:remove")
    @Log(title = "弹幕", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(blueLeaveMessageService.deleteBlueLeaveMessageByIds(ids));
    }
}
