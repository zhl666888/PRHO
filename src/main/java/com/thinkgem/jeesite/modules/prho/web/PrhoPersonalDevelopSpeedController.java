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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.prho.entity.PrhoPersonalDevelopSpeed;
import com.thinkgem.jeesite.modules.prho.service.PrhoPersonalDevelopSpeedService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectInfoService;
import com.thinkgem.jeesite.modules.prho.service.PrhoProjectTaskService;

/**
 * 个人开发速度分析Controller
 * @author zhl
 * @version 2017-06-01
 */
@Controller
@RequestMapping(value = "${adminPath}/prho/prhoPersonalDevelopSpeed")
public class PrhoPersonalDevelopSpeedController extends BaseController {

	@Autowired
	private PrhoPersonalDevelopSpeedService prhoPersonalDevelopSpeedService;
	@Autowired
	private PrhoProjectTaskService prhoProjectTaskService;
	@Autowired
	private PrhoProjectInfoService prhoProjectInfoService;
	
	@ModelAttribute
	public PrhoPersonalDevelopSpeed get(@RequestParam(required=false) String id) {
		PrhoPersonalDevelopSpeed entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = prhoPersonalDevelopSpeedService.get(id);
		}
		if (entity == null){
			entity = new PrhoPersonalDevelopSpeed();
		}
		return entity;
	}
	
	@RequiresPermissions("prho:prhoPersonalDevelopSpeed:view")
	@RequestMapping(value = {"list", ""})
	public String list(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed, HttpServletRequest request, HttpServletResponse response, Model model,@RequestParam(required = false) String radioval) {
		if(StringUtils.isNotBlank(radioval)){
			prhoPersonalDevelopSpeed.setRadioval(radioval);
		}else{
			prhoPersonalDevelopSpeed.setRadioval("day");
		}
		Page<PrhoPersonalDevelopSpeed> page = prhoPersonalDevelopSpeedService.findPageBy(new Page<PrhoPersonalDevelopSpeed>(request, response), prhoPersonalDevelopSpeed); 
		model.addAttribute("time",radioval);
		model.addAttribute("page", page);
		return "modules/prho/prhoPersonalDevelopSpeedList";
	}
	/**
	 * 导出个人开发速度分析数据
	 * @param prhoPersonalDevelopSpeed
	 * @param request
	 * @param response
	 * @param redirectAttributess
	 * @return
	 */
	  @RequiresPermissions("prho:prhoPersonalDevelopSpeed:view")
	  @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PrhoPersonalDevelopSpeed prhoPersonalDevelopSpeed, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "个人开发速度分析数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PrhoPersonalDevelopSpeed> page = prhoPersonalDevelopSpeedService.findPageBy(new Page<PrhoPersonalDevelopSpeed>(request, response, -1), prhoPersonalDevelopSpeed);
            new ExportExcel("个人开发速度分析", PrhoPersonalDevelopSpeed.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出个人开发速度分析数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/prho/prhoPersonalDevelopSpeed/list?repage";
    }

}