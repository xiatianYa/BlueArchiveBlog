package com.blue.blog.controller;

import com.blue.blog.domain.BlueArticle;
import com.blue.blog.service.IBlueArticleService;
import com.blue.common.core.utils.poi.ExcelUtil;
import com.blue.common.core.web.controller.BaseController;
import com.blue.common.core.web.domain.AjaxResult;
import com.blue.common.core.web.page.TableDataInfo;
import com.blue.common.log.annotation.Log;
import com.blue.common.log.enums.BusinessType;
import com.blue.common.security.annotation.RequiresPermissions;
import com.blue.sort.domain.BlueArticleTag;
import com.blue.sort.service.IBlueArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 文章Controller
 * 
 * @author ruoyi
 * @date 2024-03-23
 */
@RestController
@RequestMapping("/article")
public class BlueArticleController extends BaseController
{
    @Autowired
    private IBlueArticleService blueArticleService;
    @Autowired
    private IBlueArticleTagService blueArticleTagService;

    /**
     * 查询文章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BlueArticle blueArticle)
    {
        startPage();
        List<BlueArticle> list = blueArticleService.selectBlueArticleList(blueArticle);
        return getDataTable(list);
    }
    /**
     * 根据标签ID查询文章列表
     */
    @GetMapping("/listByTagId/{tagId}")
    public TableDataInfo listByTagId(@PathVariable("tagId")Long tagId){
        startPage();
        List<BlueArticle> list = blueArticleService.selectBlueArticleListByTagId(tagId);
        return getDataTable(list);
    }
    /**
     * 根据分类ID查询文章列表
     */
    @GetMapping("/listBySortId/{sortId}")
    public TableDataInfo listBySortId(@PathVariable(value = "sortId")Long tagId){
        startPage();
        List<BlueArticle> articleList = blueArticleService.selectBlueArticleListBySortId(tagId);
        //所有标签列表
        List<BlueArticleTag> blueArticleTagList = blueArticleTagService.selectBlueArticleTagList(new BlueArticleTag());
        //获取每篇文章的标签
        for (BlueArticle blueArticle : articleList) {
            //初始化
            blueArticle.setTagList(new ArrayList<>());
            for (BlueArticleTag blueArticleTag : blueArticleTagList) {
                //比对成功
                if (blueArticleTag.getArticleId().equals(blueArticle.getId())){
                    blueArticle.getTagList().add(blueArticleTag);
                }
            }
        }
        return getDataTable(articleList);
    }
    /**
     * 导出文章列表
     */
    @RequiresPermissions("blog:article:export")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlueArticle blueArticle)
    {
        List<BlueArticle> list = blueArticleService.selectBlueArticleList(blueArticle);
        ExcelUtil<BlueArticle> util = new ExcelUtil<BlueArticle>(BlueArticle.class);
        util.exportExcel(response, list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blueArticleService.selectBlueArticleById(id));
    }

    /**
     * 新增文章
     */
    @RequiresPermissions("blog:article:add")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlueArticle blueArticle)
    {
        return toAjax(blueArticleService.insertBlueArticle(blueArticle));
    }

    /**
     * 修改文章
     */
    @RequiresPermissions("blog:article:edit")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlueArticle blueArticle)
    {
        //如果是Base64编码 则进行解码
        if (blueArticle.getContent().startsWith("base64:")){
            blueArticle.setContent(new String(Base64.getDecoder().decode(blueArticle.getContent().substring("base64:".length()))));
        }
        return toAjax(blueArticleService.updateBlueArticle(blueArticle));
    }

    /**
     * 删除文章
     */
    @RequiresPermissions("blog:article:remove")
    @Log(title = "文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blueArticleService.deleteBlueArticleByIds(ids));
    }
}
