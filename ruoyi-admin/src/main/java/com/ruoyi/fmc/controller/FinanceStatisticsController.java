package com.ruoyi.fmc.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.fmc.domain.FinanceStatistics;
import com.ruoyi.fmc.service.IFinanceStatisticsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 财务统计Controller
 * 
 * @author ruoyi
 * @date 2025-09-15
 */
@Controller
@RequestMapping("/fmc/fmcstatistics")
public class FinanceStatisticsController extends BaseController
{
    private String prefix = "fmc/fmcstatistics";

    @Autowired
    private IFinanceStatisticsService financeStatisticsService;

    @RequiresPermissions("fmc:fmcstatistics:view")
    @GetMapping()
    public String fmcstatistics()
    {
        return prefix + "/fmcstatistics";
    }

    /**
     * 查询财务统计列表
     */
    @RequiresPermissions("fmc:fmcstatistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceStatistics financeStatistics)
    {
        startPage();
        List<FinanceStatistics> list = financeStatisticsService.selectFinanceStatisticsList(financeStatistics);
        return getDataTable(list);
    }

    /**
     * 导出财务统计列表
     */
    @RequiresPermissions("fmc:fmcstatistics:export")
    @Log(title = "财务统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceStatistics financeStatistics)
    {
        List<FinanceStatistics> list = financeStatisticsService.selectFinanceStatisticsList(financeStatistics);
        ExcelUtil<FinanceStatistics> util = new ExcelUtil<FinanceStatistics>(FinanceStatistics.class);
        return util.exportExcel(list, "财务统计数据");
    }

    /**
     * 新增财务统计
     */
    @RequiresPermissions("fmc:fmcstatistics:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存财务统计
     */
    @RequiresPermissions("fmc:fmcstatistics:add")
    @Log(title = "财务统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceStatistics financeStatistics)
    {
        return toAjax(financeStatisticsService.insertFinanceStatistics(financeStatistics));
    }

    /**
     * 修改财务统计
     */
    @RequiresPermissions("fmc:fmcstatistics:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FinanceStatistics financeStatistics = financeStatisticsService.selectFinanceStatisticsById(id);
        mmap.put("financeStatistics", financeStatistics);
        return prefix + "/edit";
    }

    /**
     * 修改保存财务统计
     */
    @RequiresPermissions("fmc:fmcstatistics:edit")
    @Log(title = "财务统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceStatistics financeStatistics)
    {
        return toAjax(financeStatisticsService.updateFinanceStatistics(financeStatistics));
    }

    /**
     * 删除财务统计
     */
    @RequiresPermissions("fmc:fmcstatistics:remove")
    @Log(title = "财务统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeStatisticsService.deleteFinanceStatisticsByIds(ids));
    }
}
