package com.blue.game.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.game.domain.BlueGameLeave;
import com.blue.game.domain.dto.BlueGameLeaveResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 游戏留言Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-17
 */
@Mapper
public interface BlueGameLeaveMapper extends BaseMapper<BlueGameLeave> {
    /**
     * 查询游戏留言
     *
     * @param id 游戏留言主键
     * @return 游戏留言
     */
    public BlueGameLeave selectBlueGameLeaveById(Long id);

    /**
     * 查询游戏留言列表
     *
     * @param blueGameLeave 游戏留言
     * @return 游戏留言集合
     */
    public List<BlueGameLeave> selectBlueGameLeaveList(BlueGameLeave blueGameLeave);

    /**
     * 新增游戏留言
     *
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    public int insertBlueGameLeave(BlueGameLeave blueGameLeave);

    /**
     * 修改游戏留言
     *
     * @param blueGameLeave 游戏留言
     * @return 结果
     */
    public int updateBlueGameLeave(BlueGameLeave blueGameLeave);

    /**
     * 删除游戏留言
     *
     * @param id 游戏留言主键
     * @return 结果
     */
    public int deleteBlueGameLeaveById(Long id);

    /**
     * 批量删除游戏留言
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlueGameLeaveByIds(Long[] ids);

    List<BlueGameLeaveResDto> selectBlueGameLeaveListV2(BlueGameLeave blueGameLeave);
}
