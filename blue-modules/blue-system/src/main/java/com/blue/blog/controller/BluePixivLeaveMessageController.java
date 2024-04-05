package com.blue.blog.controller;

import com.blue.blog.domain.BluePixivLeaveMessage;
import com.blue.blog.service.IBluePixivLeaveMessageService;
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
 * 番剧弹幕Controller
 * 
 * @author ruoyi
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/pixivMessage")
public class BluePixivLeaveMessageController extends BaseController
{
    @Autowired
    private IBluePixivLeaveMessageService bluePixivLeaveMessageService;

    /**
     * 查询番剧弹幕列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        startPage();
        List<BluePixivLeaveMessage> list = bluePixivLeaveMessageService.selectBluePixivLeaveMessageList(bluePixivLeaveMessage);
        return getDataTable(list);
    }

    /**
     * 导出番剧弹幕列表
     */
    @RequiresPermissions("blog:message:export")
    @Log(title = "番剧弹幕", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        List<BluePixivLeaveMessage> list = bluePixivLeaveMessageService.selectBluePixivLeaveMessageList(bluePixivLeaveMessage);
        ExcelUtil<BluePixivLeaveMessage> util = new ExcelUtil<BluePixivLeaveMessage>(BluePixivLeaveMessage.class);
        util.exportExcel(response, list, "番剧弹幕数据");
    }

    /**
     * 获取番剧弹幕详细信息
     */
    @RequiresPermissions("blog:message:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bluePixivLeaveMessageService.selectBluePixivLeaveMessageById(id));
    }

    /**
     * 新增番剧弹幕
     */
    @Log(title = "番剧弹幕", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        return toAjax(bluePixivLeaveMessageService.insertBluePixivLeaveMessage(bluePixivLeaveMessage));
    }

    /**
     * 修改番剧弹幕
     */
    @RequiresPermissions("blog:message:edit")
    @Log(title = "番剧弹幕", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BluePixivLeaveMessage bluePixivLeaveMessage)
    {
        return toAjax(bluePixivLeaveMessageService.updateBluePixivLeaveMessage(bluePixivLeaveMessage));
    }

    /**
     * 删除番剧弹幕
     */
    @RequiresPermissions("blog:message:remove")
    @Log(title = "番剧弹幕", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bluePixivLeaveMessageService.deleteBluePixivLeaveMessageByIds(ids));
    }
}
