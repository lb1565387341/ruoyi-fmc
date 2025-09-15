package com.ruoyi.fmc.service;

import java.util.List;
import com.ruoyi.fmc.domain.FinanceStatistics;

/**
 * 财务统计Service接口
 * 
 * @author ruoyi
 * @date 2025-09-15
 */
public interface IFinanceStatisticsService 
{
    /**
     * 查询财务统计
     * 
     * @param id 财务统计主键
     * @return 财务统计
     */
    public FinanceStatistics selectFinanceStatisticsById(Long id);

    /**
     * 查询财务统计列表
     * 
     * @param financeStatistics 财务统计
     * @return 财务统计集合
     */
    public List<FinanceStatistics> selectFinanceStatisticsList(FinanceStatistics financeStatistics);

    /**
     * 新增财务统计
     * 
     * @param financeStatistics 财务统计
     * @return 结果
     */
    public int insertFinanceStatistics(FinanceStatistics financeStatistics);

    /**
     * 修改财务统计
     * 
     * @param financeStatistics 财务统计
     * @return 结果
     */
    public int updateFinanceStatistics(FinanceStatistics financeStatistics);

    /**
     * 批量删除财务统计
     * 
     * @param ids 需要删除的财务统计主键集合
     * @return 结果
     */
    public int deleteFinanceStatisticsByIds(String ids);

    /**
     * 删除财务统计信息
     * 
     * @param id 财务统计主键
     * @return 结果
     */
    public int deleteFinanceStatisticsById(Long id);
}
