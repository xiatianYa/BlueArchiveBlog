package com.blue.game.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.blog.entry.dao.BlueArticle;
import com.blue.game.domain.BlueGameMode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 游戏模式Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-04
 */
@Mapper
public interface BlueGameModeMapper extends BaseMapper<BlueGameMode>
{
    /**
     * 查询游戏模式
     * 
     * @param id 游戏模式主键
     * @return 游戏模式
     */
    public BlueGameMode selectBlueGameModeById(Long id);

    /**
     * 查询游戏模式列表
     * 
     * @param blueGameMode 游戏模式
     * @return 游戏模式集合
     */
    public List<BlueGameMode> selectBlueGameModeList(BlueGameMode blueGameMode);

    /**
     * 新增游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    public int insertBlueGameMode(BlueGameMode blueGameMode);

    /**
     * 修改游戏模式
     * 
     * @param blueGameMode 游戏模式
     * @return 结果
     */
    public int updateBlueGameMode(BlueGameMode blueGameMode);

    /**
     * 删除游戏模式
     * 
     * @param id 游戏模式主键
     * @return 结果
     */
    public int deleteBlueGameModeById(Long id);

    /**
     * 批量删除游戏模式
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueGameModeByIds(Long[] ids);
}
