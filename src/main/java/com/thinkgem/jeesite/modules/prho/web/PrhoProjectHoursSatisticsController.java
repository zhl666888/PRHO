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
import com.thinkgem.jeesite.modules.prho.entity.PrhoProjectHoursStatics;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectHoursSatisticsService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 统计Controller
 * @author ldx
 * @version 2017-05-17
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoProjectHoursSatistic")
public class PrhoProjectHoursSatisticsController extends BaseController {

	@Autowired
	 private PrhoProjectHoursSatisticsService prhoProjectHoursSatisticsService;
	@ModelAttribute
     public PrhoProjectHoursStatics get(@RequestParam(required=false) String id) {
		PrhoProjectHoursStatics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoProjectHoursSatisticsService.get(id);
		}
		if (entity == null){
			entity = new PrhoProjectHoursStatics();
		}
		return entity;
	}
	
	/**
	 * 项目工时统计查询
	 * @param prhoProjectHoursStatics
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequiresPermissions("prho:prhoProjectHoursSatistic:view")
	@RequestMapping(value = {"list", ""})
	 public String hoursSatisticList(PrhoProjectHoursStatics prhoProjectHoursStatics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PrhoProjectHoursStatics> page = prhoProjectHoursSatisticsService.findPageBy(new Page<PrhoProjectHoursStatics>(request, response), prhoProjectHoursStatics); 
		model.addAttribute("page", page);
		return "modules/prho/prhoProjectHoursSatisticList";
	}
	
	/**
	 * 导出工时统计数据
	 * @param prhoProjectHoursStatics
	 * @param request
	 * @param response
	 * @param redirectAttributess
	 * @return
	 */
	//@RequiresPermissions("prho:prhoProjectHoursSatistic:view")
	  @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PrhoProjectHoursStatics prhoProjectHoursStatics, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "工时统计数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PrhoProjectHoursStatics> page = prhoProjectHoursSatisticsService.findHoursSatisticsListForExport(new Page<PrhoProjectHoursStatics>(request, response, -1), prhoProjectHoursStatics);
            new ExportExcel("工时统计数据", PrhoProjectHoursStatics.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出工时统计数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/prho/prhoProjectHoursSatistic/list?repage";
    }


}