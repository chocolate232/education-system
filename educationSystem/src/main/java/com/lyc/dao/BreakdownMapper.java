package com.lyc.dao;


import com.lyc.bean.Breakdown;

import org.apache.ibatis.annotations.Param;



import java.util.List;
import java.util.Map;

/**
 * 故障表Mybatis映射
 */

public interface BreakdownMapper {
    /**
     * 添加故障
     */
    int insertBreakdown(Map<String, Object> params);
    /**
     * 修改故障
     */
    int updateBreakdown(Map<String, Object> params);
    /**
     * 删除故障,支持批量删除
     */
    int deleteBreakdown(@Param("breakdownId") String breakdownId);
    /**
     * 查看故障
     */
    List<Breakdown> selectBreakdown(Map<String, Object> params);
    /**
     * 查询故障数量
     */
    int selectBreakdownCount(Map<String, Object> params);
    /**
     * 值班教师点击去处理，将故障处理人和在故障状态修改,修改之前先查询一遍，看这个故障处理人是否为空，状态是否为0
     */
    /**
     * 先查询
     */
    int selectSloveBy(@Param("breakdownId") String breakdownId);
    /**
     * 再修改状态
     */
    int updateBreakdownState(Map<String, Object> params);
    /**
     * 管理员修改故障的状态
     */
    int adminUpdateState(Map<String, Object> params);
}
