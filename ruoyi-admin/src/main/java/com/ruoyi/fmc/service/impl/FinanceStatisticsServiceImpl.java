package com.ruoyi.fmc.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fmc.mapper.FinanceStatisticsMapper;
import com.ruoyi.fmc.domain.FinanceStatistics;
import com.ruoyi.fmc.service.IFinanceStatisticsService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.UtilException;
import com.ruoyi.common.utils.StringUtils;

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

    /**
     * 导入财务统计数据
     * 
     * @param financeStatisticsList 财务统计数据列表
     * @param updateSupport 是否更新已存在的数据
     * @return 结果
     */
    @Override
    public String importFinanceStatistics(List<FinanceStatistics> financeStatisticsList, boolean updateSupport) {
        if (StringUtils.isNull(financeStatisticsList) || financeStatisticsList.size() == 0) {
            throw new UtilException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        
        for (FinanceStatistics financeStatistics : financeStatisticsList) {
            try {
                // 验证数据有效性
                if (StringUtils.isNull(financeStatistics.getProductId())) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、商品ID不能为空");
                    continue;
                }
                
                // 判断是否已存在
                FinanceStatistics queryFinanceStatistics = new FinanceStatistics();
                queryFinanceStatistics.setProductId(financeStatistics.getProductId());
                queryFinanceStatistics.setStatTime(financeStatistics.getStatTime());
                List<FinanceStatistics> list = selectFinanceStatisticsList(queryFinanceStatistics);
                
                if (list == null || list.size() == 0) {
                    // 不存在则插入
                    insertFinanceStatistics(financeStatistics);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、商品ID " + financeStatistics.getProductId() + " 导入成功");
                } else if (updateSupport) {
                    // 存在且允许更新
                    financeStatistics.setId(list.get(0).getId());
                    updateFinanceStatistics(financeStatistics);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、商品ID " + financeStatistics.getProductId() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、商品ID " + financeStatistics.getProductId() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、商品ID " + financeStatistics.getProductId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new UtilException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}