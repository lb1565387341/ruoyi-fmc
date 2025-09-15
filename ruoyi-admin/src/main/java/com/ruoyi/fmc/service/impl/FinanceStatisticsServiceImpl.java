package com.ruoyi.fmc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmc.mapper.FinanceStatisticsMapper;
import com.ruoyi.fmc.domain.FinanceStatistics;
import com.ruoyi.fmc.service.IFinanceStatisticsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 财务统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-15
 */
@Service
public class FinanceStatisticsServiceImpl implements IFinanceStatisticsService 
{
    @Autowired
    private FinanceStatisticsMapper financeStatisticsMapper;

    /**
     * 查询财务统计
     * 
     * @param id 财务统计主键
     * @return 财务统计
     */
    @Override
    public FinanceStatistics selectFinanceStatisticsById(Long id)
    {
        return financeStatisticsMapper.selectFinanceStatisticsById(id);
    }

    /**
     * 查询财务统计列表
     * 
     * @param financeStatistics 财务统计
     * @return 财务统计
     */
    @Override
    public List<FinanceStatistics> selectFinanceStatisticsList(FinanceStatistics financeStatistics)
    {
        return financeStatisticsMapper.selectFinanceStatisticsList(financeStatistics);
    }

    /**
     * 新增财务统计
     * 
     * @param financeStatistics 财务统计
     * @return 结果
     */
    @Override
    public int insertFinanceStatistics(FinanceStatistics financeStatistics)
    {
        financeStatistics.setCreateTime(DateUtils.getNowDate());
        return financeStatisticsMapper.insertFinanceStatistics(financeStatistics);
    }

    /**
     * 修改财务统计
     * 
     * @param financeStatistics 财务统计
     * @return 结果
     */
    @Override
    public int updateFinanceStatistics(FinanceStatistics financeStatistics)
    {
        financeStatistics.setUpdateTime(DateUtils.getNowDate());
        return financeStatisticsMapper.updateFinanceStatistics(financeStatistics);
    }

    /**
     * 批量删除财务统计
     * 
     * @param ids 需要删除的财务统计主键
     * @return 结果
     */
    @Override
    public int deleteFinanceStatisticsByIds(String ids)
    {
        return financeStatisticsMapper.deleteFinanceStatisticsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除财务统计信息
     * 
     * @param id 财务统计主键
     * @return 结果
     */
    @Override
    public int deleteFinanceStatisticsById(Long id)
    {
        return financeStatisticsMapper.deleteFinanceStatisticsById(id);
    }
}
