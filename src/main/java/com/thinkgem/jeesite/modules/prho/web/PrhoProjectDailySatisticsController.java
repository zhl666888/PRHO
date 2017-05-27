/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.prho.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectDailyStatics;
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHoursStatics;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectDailySatisticsService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 统计Controller
 * @author ldx
 * @version 2017-05-23
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectDailySatistic")
public class PrhoProjectDailySatisticsController extends BaseController {

	@Autowired
	 private PrhoProjectDailySatisticsService prhoProjectDailySatisticsService;
	
	@ModelAttribute
     public PrhoProjectDailyStatics get(@RequestParam(required=false) String id) {
		PrhoProjectDailyStatics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectDailySatisticsService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectDailyStatics();
		}
		return entity;
	}
	
	/**
	 * 日报统计查询
	 * @param PrhoProjectDailyStatics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequiresPermissions("prho:prhoProjectDailySatistic:view")
	@RequestMapping(value = {"list", ""})
	 public String dailySatisticList(PrhoProjectDailyStatics prhoProjectDailyStatics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoProjectDailyStatics> page = prhoProjectDailySatisticsService.findPageBy(new Page<PrhoProjectDailyStatics>(request, response), prhoProjectDailyStatics); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectDailySatisticList";
	}
	/**
	 * 导出日报统计数据
	 * @param prhoProjectHoursStatics
	 * @param request
	 * @param response
	 * @param redirectAttributess
	 * @return
	 */
	//@RequiresPermissions("prho:prhoProjectHoursSatistic:view")
	  @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PrhoProjectDailyStatics prhoProjectDailyStatics, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "日报统计数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PrhoProjectDailyStatics> page = prhoProjectDailySatisticsService.findDailySatisticsListForExport(new Page<PrhoProjectDailyStatics>(request, response, -1), prhoProjectDailyStatics);
            new ExportExcel("日报统计数据", PrhoProjectDailyStatics.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出日报统计数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/prho/prhoProjectHoursSatistic/list?repage";
    }


}